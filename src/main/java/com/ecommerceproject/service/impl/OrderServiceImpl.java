package com.ecommerceproject.service.impl;

import com.ecommerceproject.payload.request.UpdateOrderRequest;
import com.ecommerceproject.repository.OrderRepository;
import com.ecommerceproject.service.OrderService;
import com.ecommerceproject.converter.OrderConverter;
import com.ecommerceproject.dto.OrderDTO;
import com.ecommerceproject.entity.Order;
import com.ecommerceproject.entity.ProductSize;
import com.ecommerceproject.entity.ProductSizeKey;
import com.ecommerceproject.exception.DataNotFoundException;
import com.ecommerceproject.repository.ProductSizeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderConverter orderConverter;
    @Autowired
    private ProductSizeRepository productSizeRepository;


    @Override
    public OrderDTO updateOrder(UpdateOrderRequest request) throws DataNotFoundException {
        if(!orderRepository.existsById(request.getOrderId())) {
            throw new DataNotFoundException("Can not find order");
        }
        Order order = orderRepository.findById(request.getOrderId()).get();
        Integer preQuantity = order.getQuantity();
        order.setQuantity(request.getQuantity());
        orderRepository.save(order);
        ProductSizeKey productSizeKey = new ProductSizeKey(order.getProduct().getId(), order.getSize());
        ProductSize productSize = productSizeRepository.findById((productSizeKey)).get();
        productSize.setQuantity(productSize.getQuantity() + (preQuantity - request.getQuantity()));
        productSizeRepository.save(productSize);
        return orderConverter.toDTO(order);
    }

    @Override
    @Transactional
    public void deleteOrder(Integer[] orderIds) throws DataNotFoundException {
        for(Integer orderId: orderIds) {
            if(!orderRepository.existsById(orderId)) {
                throw new DataNotFoundException("Can not find order");
            }
            else {
                Order order = orderRepository.findById(orderId).get();
                ProductSizeKey productSizeKey = new ProductSizeKey(order.getProduct().getId(), order.getSize());
                ProductSize productSize = productSizeRepository.findById((productSizeKey)).get();
                productSize.setQuantity(productSize.getQuantity() + order.getQuantity());
                orderRepository.deleteById(orderId);
            }
        }
    }
}
