package com.example.ecommerceproject.exception;

import lombok.Getter;

@Getter
public class LoginFailException extends Exception{
    private Object data;
    public LoginFailException(String message, Object data) {
        super(message);
        this.data = data;
    }
}
