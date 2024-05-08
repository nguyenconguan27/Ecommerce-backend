package com.example.ecommerceproject.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@Builder
public class UserUpdateRequest {
    private String address;
    @Email(message = "Email is invalidate")
    private String email;
    @NotBlank(message = "fullName is mandatory")
    private String fullName;
    @Pattern(regexp = "(((\\\\+|)84)|0)(3|5|7|8|9)+([0-9]{8})\\\\b", message = "Number phone is invalidate")
    private String number;
    @NotBlank(message = "username is mandatory")
    private String username;
    private String image;
}
