package com.projetjava.main.entity;

public class ApiResponse {

    private String message;
    public ApiResponse(String message)
    {
        this.message = message;
    }

    public String getMessage()
    {
        return this.message;
    }
}
