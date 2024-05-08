package com.example.ecommerceproject.payload.response;

import com.example.ecommerceproject.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class LoginResponse {
    private UserDTO userDTO;
    private String accessToken;
    private String refreshToken;
}
