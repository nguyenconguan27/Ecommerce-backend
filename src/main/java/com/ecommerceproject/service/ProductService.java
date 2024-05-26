package com.ecommerceproject.service;

import com.ecommerceproject.dto.ProductDTO;
import com.ecommerceproject.payload.request.ProductProcessRequest;
import com.ecommerceproject.payload.response.ProductListResponse;
import org.springframework.data.domain.Pageable;


public interface ProductService {
    ProductDTO getOne(Integer id) throws Exception;
    ProductDTO update(ProductProcessRequest product) throws Exception;
    void delete(Integer id) throws Exception;
    ProductListResponse getByOptions(String title, Integer categoryId, Integer priceMin, Integer priceMax, Pageable pageable) throws Exception;

    ProductDTO add(ProductProcessRequest product) throws Exception;
}
