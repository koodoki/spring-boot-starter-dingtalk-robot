package top.nextor.dingtalk.robot.boot.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import top.nextor.dingtalk.robot.DingTalkClient;
import top.nextor.dingtalk.robot.DingTalkRequest;
import top.nextor.dingtalk.robot.DingTalkResponse;

/**
 * 参考文档 https://ding-doc.dingtalk.com/doc#/serverapi2/qf2nxq/d535db33
 */

@SpringBootTest
@SpringBootApplication
public class DingTalkApplicationTest {

    @Autowired
    private DingTalkClient dingTalkClient;

    @Test
    public void testText() {
        DingTalkRequest request = DingTalkRequest.of(DingTalkRequest.MessageType.text);
        request.setText(DingTalkRequest.Text.of("世界上最不争气的国家，中国全力相助20年，如今却仍穷得喝西北风"));
        request.setAt(DingTalkRequest.AT.of(true));

        DingTalkResponse response = dingTalkClient.execute(request);
        assert response.getCode() == 0;
    }

    @Test
    public void testLink() {
        DingTalkRequest request = DingTalkRequest.of(DingTalkRequest.MessageType.link);
        request.setLink(DingTalkRequest.Link.of("世界上最不争气的国家，中国全力相助20年，如今却仍穷得喝西北风",
                "我国有一个词语叫做“扶不起的阿斗”，常常被用来形容一些始终无法扶持成才的人，今天要说的这个国家，也让人颇有这种感觉，它就是阿尔巴尼亚，虽然现在已经很少听到这个国家了，但在上世纪的中国，它可是中国人都非常熟悉的。",
                "https://pics1.baidu.com/feed/8cb1cb134954092329f4650f1bd7200cb2de49b3.jpeg?token=125fe07a24c8934af7b691f23aecc4c4&s=C708EEA256885EF55694450E0300B052",
                "https://mbd.baidu.com/newspage/data/landingsuper?context=%7B%22nid%22%3A%22news_9037801377663267547%22%7D&n_type=0&p_from=1"));

        DingTalkResponse response = dingTalkClient.execute(request);
        assert response.getCode() == 0;
    }

    @Test
    public void testMarkdown() {
        DingTalkRequest request = DingTalkRequest.of(DingTalkRequest.MessageType.markdown);
        request.setMarkdown(DingTalkRequest.Markdown.of("世界上最不争气的国家，中国全力相助20年，如今却仍穷得喝西北风",
                "> 我国有一个词语叫做“扶不起的阿斗”，常常被用来形容一些始终无法扶持成才的人，今天要说的这个国家，也让人颇有这种感觉，它就是阿尔巴尼亚，虽然现在已经很少听到这个国家了，但在上世纪的中国，它可是中国人都非常熟悉的。 \n  > 上世纪60年代开始，中国与阿尔巴尼亚曾有过一段非常密切的交往，中国对此国援助了20年之久，然而直到今天，阿尔巴尼亚仍然是欧洲最穷的国家，俗话说“事在人为”，按理说阿尔巴尼亚地理位置不差、资源不差、还有他国援助，为何就是“扶不起来”呢？ \n  > 阿尔巴尼亚，位于欧洲东南部，巴尔干半岛西南部的国家，它东临马其顿，南临希腊，是阿尔干半岛上最古老的民族之一，此国矿产资源、水力资源都挺丰富的，甚至被尊称为“欧洲明灯”、“亚德里亚海的雄鹰”。"));
        request.setAt(DingTalkRequest.AT.of(true));

        DingTalkResponse response = dingTalkClient.execute(request);
        assert response.getCode() == 0;
    }

    @Test
    public void testSingleActionCard() {
        DingTalkRequest request = DingTalkRequest.of(DingTalkRequest.MessageType.actionCard);
        request.setActionCard(DingTalkRequest.SingleActionCard.of("世界上最不争气的国家，中国全力相助20年，如今却仍穷得喝西北风",
                "我国有一个词语叫做“扶不起的阿斗”，常常被用来形容一些始终无法扶持成才的人，今天要说的这个国家，也让人颇有这种感觉，它就是阿尔巴尼亚，虽然现在已经很少听到这个国家了，但在上世纪的中国，它可是中国人都非常熟悉的。",
                "0",
                "0",
                "阅读全文",
                "https://mbd.baidu.com/newspage/data/landingsuper?context=%7B%22nid%22%3A%22news_9037801377663267547%22%7D&n_type=0&p_from=1"));

        DingTalkResponse response = dingTalkClient.execute(request);
        assert response.getCode() == 0;
    }

    @Test
    public void testMultiActionCard() {
        DingTalkRequest request = DingTalkRequest.of(DingTalkRequest.MessageType.actionCard);
        request.setActionCard(DingTalkRequest.MultiActionCard.of("乔布斯 20 年前想打造一间苹果咖啡厅，而它正是 Apple Store 的前身",
                "![screenshot](@lADOpwk3K80C0M0FoA)\n  ### 乔布斯 20 年前想打造的苹果咖啡厅 \n  Apple Store 的设计正从原来满满的科技感走向生活化，而其生活化的走向其实可以追溯到 20 年前苹果一个建立咖啡馆的计划",
                "1",
                "1",
                DingTalkRequest.MultiActionCard.Action.of("内容不错", "https://ding-doc.dingtalk.com/"),
                DingTalkRequest.MultiActionCard.Action.of("不感兴趣", "https://www.baidu.com/")
        ));

        DingTalkResponse response = dingTalkClient.execute(request);
        assert response.getCode() == 0;
    }

    @Test
    public void testFeedCard() {
        DingTalkRequest request = DingTalkRequest.of(DingTalkRequest.MessageType.feedCard);
        request.setFeedCard(DingTalkRequest.FeedCard.of(
                DingTalkRequest.FeedCard.Link.of("中日韩领导人会议为什么选在成都举行？", "https://mbd.baidu.com/newspage/data/landingsuper?context=%7B%22nid%22%3A%22news_10042929213730806059%22%7D&n_type=0&p_from=1", "https://pics1.baidu.com/feed/f9198618367adab442235e3d2e5342198601e46e.jpeg?token=a839cdffe7251732fdba9d319be16cd7&s=FF9B768D16137BC46621A4E10300F0B7"),
                DingTalkRequest.FeedCard.Link.of("世界上最不争气的国家，中国全力相助20年，如今却仍穷得喝西北风", "https://mbd.baidu.com/newspage/data/landingsuper?context=%7B%22nid%22%3A%22news_9037801377663267547%22%7D&n_type=0&p_from=1", "https://pics1.baidu.com/feed/0df3d7ca7bcb0a4682e9e716e2ec07216a60af6f.jpeg?token=fe23e82c6a0b7bee7ab912531891c0fa&s=BDF0CB1451C3D5451A6C14D40300D030"),
                DingTalkRequest.FeedCard.Link.of("他是老蒋与宋美龄的媒人，主动把宋美龄让给老蒋，成全了老蒋", "https://mbd.baidu.com/newspage/data/landingsuper?context=%7B%22nid%22%3A%22news_9323134712348033369%22%7D&n_type=0&p_from=1", "https://pics7.baidu.com/feed/e61190ef76c6a7ef97c78a6fd6985e54f2de6679.jpeg?token=38525cb5d9a129ea9c11488ff05762c9&s=17787F8640232EA4AD3D99AF03003013")
        ));

        DingTalkResponse response = dingTalkClient.execute(request);
        assert response.getCode() == 0;
    }
}
