package com.example.ecommerceproject.payload.response;

import com.example.ecommerceproject.dto.ProductDTO;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductListResponse {
    private List<ProductDTO> productList;
    private Integer totalPage;
}
