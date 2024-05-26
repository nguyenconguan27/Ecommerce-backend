package com.ecommerceproject.service.redis;

import com.ecommerceproject.dto.ProductDTO;
import com.ecommerceproject.payload.response.ProductListResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductRedisService {
    void clear();
    ProductListResponse getAllProducts(
            String title,
            String sortField,
            Integer CategoryId, Pageable pageRequest
    ) throws JsonProcessingException;
    void saveAllProducts(ProductListResponse productDTOList,
                         String title,
                         String sortField,
                         Integer CategoryId,
                         Pageable pageRequest) throws JsonProcessingException;
}
