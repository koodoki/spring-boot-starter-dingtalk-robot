package top.nextor.dingtalk.robot;

import feign.Headers;
import feign.RequestLine;

public interface DingTalkClient {

    @Headers("Content-Type: application/json")
    @RequestLine("POST /robot/send")
    DingTalkResponse execute(DingTalkRequest request);

}
