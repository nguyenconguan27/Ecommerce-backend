package com.ecommerceproject.service;

import com.ecommerceproject.payload.request.UpdateOrderRequest;
import com.ecommerceproject.dto.OrderDTO;
import com.ecommerceproject.exception.DataNotFoundException;

public interface OrderService {

    OrderDTO updateOrder(UpdateOrderRequest request) throws DataNotFoundException;
    void deleteOrder(Integer[] orderId) throws DataNotFoundException;
}
