package com.example.ecommerceproject.service;

import com.example.ecommerceproject.exception.DataConfictException;
import com.example.ecommerceproject.exception.LoginFailException;
import com.example.ecommerceproject.payload.request.LoginRequest;
import com.example.ecommerceproject.payload.request.RegisterRequest;
import com.example.ecommerceproject.payload.response.LoginResponse;
import com.example.ecommerceproject.payload.response.RefreshTokenResponse;
import io.jsonwebtoken.ExpiredJwtException;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest) throws LoginFailException;

    LoginResponse register(RegisterRequest registerRequest) throws DataConfictException;

    RefreshTokenResponse refreshToken(String refreshToken, String userId) throws LoginFailException;

}
