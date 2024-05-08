package com.example.ecommerceproject.apis;

import com.example.ecommerceproject.exception.DataConfictException;
import com.example.ecommerceproject.exception.LoginFailException;
import com.example.ecommerceproject.payload.request.LoginRequest;
import com.example.ecommerceproject.payload.request.RegisterRequest;
import com.example.ecommerceproject.payload.response.LoginResponse;
import com.example.ecommerceproject.payload.response.ResponseObject;
import com.example.ecommerceproject.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthApis {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ResponseObject> login(@RequestBody @Valid LoginRequest loginRequest) throws LoginFailException {
        LoginResponse loginResponse = authService.login(loginRequest);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                ResponseObject.builder()
                        .message("Login successfully")
                        .httpStatus(HttpStatus.ACCEPTED)
                        .data(loginResponse)
                        .build()
        );
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseObject> register(@RequestBody @Valid RegisterRequest registerRequest) throws DataConfictException, LoginFailException {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ResponseObject.builder()
                        .message("Register successfully")
                        .httpStatus(HttpStatus.CREATED)
                        .data(authService.register(registerRequest))
                        .build()
        );

    }

    @PostMapping("/refresh-token")
    public ResponseEntity<ResponseObject> refreshToken(@RequestParam String refreshToken,
                                                       @RequestParam String username) throws LoginFailException {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                ResponseObject.builder()
                        .message("Refresh token successfully")
                        .httpStatus(HttpStatus.ACCEPTED)
                        .data(authService.refreshToken(refreshToken, username))
                        .build()
        );
    }
}
