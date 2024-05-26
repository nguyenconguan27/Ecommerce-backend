package com.ecommerceproject.payload.response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RefreshTokenResponse {
    private String accessToken;
    private String refreshToken;
}
