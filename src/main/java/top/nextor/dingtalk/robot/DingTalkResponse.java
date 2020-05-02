package top.nextor.dingtalk.robot;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DingTalkResponse {

    @JsonProperty("errcode")
    private int code;
    @JsonProperty("errmsg")
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
