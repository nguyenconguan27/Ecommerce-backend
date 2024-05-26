package com.ecommerceproject.payload.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AddToCartRequest {
    @NotNull
    private Integer productId;
    @NotNull
    private Integer quantity;
    @NotNull
    private String size;
}
