package com.example.ecommerceproject.service;

import com.example.ecommerceproject.dto.ProductDTO;
import com.example.ecommerceproject.exception.DataNotFoundException;
import com.example.ecommerceproject.payload.request.ProductProcessRequest;
import com.example.ecommerceproject.payload.response.ProductListResponse;


public interface ProductService {
    ProductDTO getOne(Integer id) throws Exception;
    ProductDTO update(ProductProcessRequest product) throws Exception;
    void delete(Integer id) throws Exception;
    ProductListResponse getByOptions(Integer pageNum, Integer pageSize, String sortField, String sortDir, String title, Integer categoryId, Integer priceMin, Integer priceMax) throws Exception;

    ProductDTO add(ProductProcessRequest product) throws Exception;
}
