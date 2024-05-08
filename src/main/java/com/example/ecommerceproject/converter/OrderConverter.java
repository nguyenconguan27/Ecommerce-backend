package com.example.ecommerceproject.converter;

import com.example.ecommerceproject.dto.OrderDTO;
import com.example.ecommerceproject.entity.*;
import com.example.ecommerceproject.repository.CartRepository;
import com.example.ecommerceproject.repository.ProductRepository;
import com.example.ecommerceproject.repository.ReceiptRepository;
import com.example.ecommerceproject.repository.ProductSizeRepository;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class OrderConverter {
    @Autowired
    private final ModelMapper modelMapper;
    @Autowired
    private final CartRepository cartRepository;
    @Autowired
    private final ProductRepository productRepository;
    @Autowired
    private final ReceiptRepository receiptRepository;
    @Autowired
    private ProductSizeRepository productSizeRepository;

    public OrderDTO toDTO(Order order) {
        OrderDTO orderDTO = modelMapper.map(order, OrderDTO.class);
        orderDTO.setCartId(order.getCart().getId());
        orderDTO.setProductId(order.getProduct().getId());
        orderDTO.setSize(order.getSize());
        List<Image> imageList = order.getProduct().getImageList();
        if(imageList.size() > 0) {
            orderDTO.setProductImage(imageList.get(0).getFileName());
        }
        return orderDTO;
    }

    public Order toEntity(OrderDTO orderDTO) {
        Order order = modelMapper.map(orderDTO, Order.class);
        Cart cart = cartRepository.findById(orderDTO.getCartId()).get();
        Product product = productRepository.findById(orderDTO.getProductId()).get();
        order.setCart(cart);
        order.setProduct(product);
        order.setSize(order.getSize());
        return order;
    }
}
