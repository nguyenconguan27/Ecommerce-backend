package com.example.ecommerceproject.exception;

import com.example.ecommerceproject.payload.request.LoginRequest;
import com.example.ecommerceproject.payload.response.ResponseObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ResponseObject> handleGeneralException(Exception exception) {
        return ResponseEntity.internalServerError().body(
                ResponseObject.builder()
                        .message(exception.getMessage())
                        .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                        .build()
        );
    }

    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ResponseObject> handleDataNotFoundException(DataNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ResponseObject.builder()
                        .message(exception.getMessage())
                        .httpStatus(HttpStatus.NOT_FOUND)
                        .build());
    }


    @ExceptionHandler(LoginFailException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ResponseObject> handleLoginFailException(LoginFailException exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                ResponseObject.builder()
                        .message(exception.getMessage())
                        .httpStatus(HttpStatus.UNAUTHORIZED)
                        .data(
                                exception.getData()
                        )
                        .build()
        );
    }

    @ExceptionHandler(DataConfictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ResponseObject> handleDataConflictException(DataConfictException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                ResponseObject.builder()
                        .message(exception.getMessage())
                        .httpStatus(HttpStatus.CONFLICT)
                        .data(exception.getData())
                        .build()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseObject> handleDataInvalidException(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ResponseObject.builder()
                        .message("Data is not in the correct format")
                        .httpStatus(HttpStatus.BAD_REQUEST)
                        .data(
                                errors
                        )
                        .build()
        );
    }

}
