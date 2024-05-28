package com.caisse.exception;

public class ApiRequestException extends RuntimeException {
    private String message;
    private String body;
    private String code;
    public ApiRequestException(String message) {
        this.message = message ;
    }
    public ApiRequestException(String message, String code) {
        this.message = message;
        this.code = code;
    }
    public ApiRequestException(String message, String body, String code) {
        this.message = message;
        this.body = body;
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}