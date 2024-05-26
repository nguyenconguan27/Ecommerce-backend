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
public class ApproveOrderRequest {
    @NotNull
    private Integer receiptId;
    @NotNull
    private Integer action;
}
