package com.ecommerceproject.payload.response;

import com.ecommerceproject.dto.ProductDTO;
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
