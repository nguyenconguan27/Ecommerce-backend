package com.example.ecommerceproject.service.impl;

import com.example.ecommerceproject.converter.OrderConverter;
import com.example.ecommerceproject.dto.OrderDTO;
import com.example.ecommerceproject.entity.*;
import com.example.ecommerceproject.payload.request.AddToCartRequest;
import com.example.ecommerceproject.repository.*;
import com.example.ecommerceproject.service.CartService;
import com.example.ecommerceproject.util.Constants;
import jakarta.transaction.Transactional;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderConverter orderConverter;
    @Autowired
    private ProductSizeRepository productSizeRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    @Transactional
    public OrderDTO addToCart(Integer cartId, AddToCartRequest addToCartRequest) throws Exception {
        ProductSizeKey productSizeKey = new ProductSizeKey(addToCartRequest.getProductId(), addToCartRequest.getSize());
        if(!cartRepository.existsById(cartId) || !productRepository.existsById(addToCartRequest.getProductId())
        || !productSizeRepository.existsById(productSizeKey)) {
            throw new Exception("Add product to cart failed");
        }
        Cart cart = cartRepository.findById(cartId).get();
        Product product = productRepository.findById(addToCartRequest.getProductId()).get();
        Order order = new Order();
        order.setCart(cart);
        order.setPrice(product.getPrice());
        order.setProduct(product);
        order.setSize(addToCartRequest.getSize());
        order.setQuantity(addToCartRequest.getQuantity());
        order.setStatus(Constants.ORDER_STATUS.INCART);
        orderRepository.save(order);
        return orderConverter.toDTO(order);
    }

    @Override
    public List<OrderDTO> getOrderInCart(Integer userId) throws Exception {
        Customer customer = customerRepository.findById(userId).get();
        List<Order> orders = orderRepository.findByCart_idAndStatus(customer.getCart().getId(), Constants.ORDER_STATUS.INCART);
        List<OrderDTO> orderDTOS = orders.stream().map(order -> orderConverter.toDTO(order)).collect(Collectors.toList());
        return orderDTOS;
    }


}
