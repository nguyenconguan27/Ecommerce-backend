package com.example.ecommerceproject.payload.response;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

@Data
@Getter
@Setter
@AllArgsConstructor
@Builder
public class ResponseObject {

    private String message;
    private HttpStatus httpStatus;
    private Object data;
}
