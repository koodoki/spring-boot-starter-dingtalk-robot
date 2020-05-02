package top.nextor.dingtalk.robot.boot;

import feign.Feign;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.nextor.dingtalk.robot.DingTalkClient;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@ConditionalOnMissingBean(DingTalkClient.class)
@EnableConfigurationProperties(value = DingTalkProperties.class)
@Configuration
public class DingTalkAutoConfiguration {

    @Bean
    public DingTalkClient dingTalkClient(DingTalkProperties dingTalkProperties) {
        return build(dingTalkProperties);
    }

    public static DingTalkClient build(DingTalkProperties properties) {
        return Feign.builder()
                .decoder(new JacksonDecoder())
                .encoder(new JacksonEncoder())
                .requestInterceptor(new DingTalkRequestInterceptor(properties))
                .target(DingTalkClient.class, properties.getBaseUrl());
    }

    private static class DingTalkRequestInterceptor implements RequestInterceptor {

        private static final Logger logger = LoggerFactory.getLogger(DingTalkRequestInterceptor.class);

        private final DingTalkProperties dingTalkProperties;

        public DingTalkRequestInterceptor(DingTalkProperties dingTalkProperties) {
            this.dingTalkProperties = dingTalkProperties;
        }

        @Override
        public void apply(RequestTemplate template) {
            template.query("access_token", dingTalkProperties.getAccessToken());

            final DingTalkProperties.Signature signature = dingTalkProperties.getSignature();
            if (signature.isEnable()) {
                final long timestamp = System.currentTimeMillis();
                try {
                    final String sign = signature.sign(timestamp);

                    template.query("timestamp", String.valueOf(timestamp))
                            .query("sign", sign);

                } catch (NoSuchAlgorithmException | InvalidKeyException e) {
                    logger.error("DingTalkRequestInterceptor|apply|error occurred when signing.", e);
                }
            }
        }
    }
}
