package com.example.gkjexam.MyBean;

import java.util.List;

/**
 * Created by 郭康杰 on 2017/8/17.
 */
public class MyGsonBean {

    /**
     * error_code : 0
     * reason : Success!
     * result : ["少年漫画","青年漫画","少女漫画","耽美漫画"]
     */

    private int error_code;
    private String reason;
    private List<String> result;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<String> getResult() {
        return result;
    }

    public void setResult(List<String> result) {
        this.result = result;
    }
}
