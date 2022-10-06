package com.techyogi.sample.shiro.model;

public class UserInfoResponse {

    private String responseCode;
    private String responseMessage;

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    //create a toString method
    @Override
    public String toString() {
        return "UserInfoResponse [responseCode=" + responseCode + ", responseMessage=" + responseMessage + "]";
    }
}
