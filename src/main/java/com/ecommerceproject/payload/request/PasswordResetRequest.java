package com.ecommerceproject.payload.request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PasswordResetRequest {
    private String oldPassword;
    private String newPassword;
    private String confirmNewPassword;
}
