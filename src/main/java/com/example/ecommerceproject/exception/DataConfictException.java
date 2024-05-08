package com.example.ecommerceproject.exception;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DataConfictException extends Exception {
    private Object data;
    public DataConfictException(String message, Object data) {
        super(message);
        this.data = data;
    }
}
