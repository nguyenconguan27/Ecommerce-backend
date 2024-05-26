package com.ecommerceproject.payload.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShippingInfoRequest {
    @NotNull
    private String address;
    @NotNull
    @Pattern(regexp = "(((\\+|)84)|0)(3|5|7|8|9)+([0-9]{8})\\b", message = "Number phone is invalidate")
    private String number;
}
