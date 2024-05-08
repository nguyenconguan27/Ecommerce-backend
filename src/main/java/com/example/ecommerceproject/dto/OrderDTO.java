package com.example.ecommerceproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Integer id;
    private Integer price;
    private Integer quantity;
    private String size;
    private Integer cartId;
    private Integer productId;
    private String productImage;
}
