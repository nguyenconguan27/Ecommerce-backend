package com.ecommerceproject.payload.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BuyProductRequest {
    @NotNull
    private List<Integer> orderIdList;
    @NotNull(message = "Shipping info is mandatory")
    private Integer shippingInfo;
    @NotNull
    private Integer payMethod;
}
