package com.ecommerceproject.service;

import com.ecommerceproject.payload.request.AddToCartRequest;
import com.ecommerceproject.dto.OrderDTO;

import java.util.List;

public interface CartService {
    OrderDTO addToCart(Integer cartId, AddToCartRequest addToCartRequest) throws Exception;
    List<OrderDTO> getOrderInCart(Integer userId) throws Exception;

}
