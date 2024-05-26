package com.ecommerceproject.service;

import com.ecommerceproject.exception.DataConfictException;
import com.ecommerceproject.exception.LoginFailException;
import com.ecommerceproject.payload.request.LoginRequest;
import com.ecommerceproject.payload.request.RegisterRequest;
import com.ecommerceproject.payload.response.LoginResponse;
import com.ecommerceproject.payload.response.RefreshTokenResponse;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest) throws LoginFailException;

    LoginResponse register(RegisterRequest registerRequest) throws DataConfictException;

    RefreshTokenResponse refreshToken(String refreshToken, String userId) throws LoginFailException;

}
