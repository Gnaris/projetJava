package com.projetjava.main.entity;

public class ResponseData {

    private boolean success;
    private String message;
    private Object data;

    public ResponseData(boolean success, String message) {
        this.success = success;
        this.message = message;
        this.data = null;
    }

    public ResponseData(boolean success, Object data) {
        this.success = success;
        this.data = data;
    }


    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }
}
