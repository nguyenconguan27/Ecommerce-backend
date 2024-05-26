package com.ecommerceproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Integer id;
    private Integer price;
    private Integer prePrice;
    private Integer quantity;
    private String size;
    private Integer cartId;
    private ProductDTO product;
}
