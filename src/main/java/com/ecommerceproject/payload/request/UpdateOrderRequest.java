package com.ecommerceproject.payload.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UpdateOrderRequest {
    @NotNull
    private Integer orderId;
    @NotNull
    private Integer quantity;
}
