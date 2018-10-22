package com.securedEdgePay.model;

public class ResponseModel {
    private String responseCode;
    private String responseMessage;
    private Object object;

    public ResponseModel() {
    }

    public ResponseModel(String responseCode, String responseMessage, Object object) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.object = object;
    }

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

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
