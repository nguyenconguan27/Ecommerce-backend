package com.example.ecommerceproject.service;

import com.example.ecommerceproject.dto.OrderDTO;
import com.example.ecommerceproject.payload.request.AddToCartRequest;

import java.util.List;

public interface CartService {
    OrderDTO addToCart(Integer cartId, AddToCartRequest addToCartRequest) throws Exception;
    List<OrderDTO> getOrderInCart(Integer userId) throws Exception;

}
