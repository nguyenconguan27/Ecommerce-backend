package com.ecommerceproject.converter;

import com.ecommerceproject.entity.Cart;
import com.ecommerceproject.entity.Image;
import com.ecommerceproject.entity.Order;
import com.ecommerceproject.repository.CartRepository;
import com.ecommerceproject.repository.ProductSizeRepository;
import com.ecommerceproject.dto.OrderDTO;
import com.ecommerceproject.repository.ProductRepository;
import com.ecommerceproject.repository.ReceiptRepository;
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
    @Autowired
    private ProductConverter productConverter;

    public OrderDTO toDTO(Order order) {
        OrderDTO orderDTO = modelMapper.map(order, OrderDTO.class);
        orderDTO.setCartId(order.getCart().getId());
        orderDTO.setPrePrice(order.getProduct().getPrePrice());
        orderDTO.setProduct(productConverter.toDTO(order.getProduct()));
        List<Image> imageList = order.getProduct().getImageList();
        return orderDTO;
    }

    public Order toEntity(OrderDTO orderDTO) {
        Order order = modelMapper.map(orderDTO, Order.class);
        Cart cart = cartRepository.findById(orderDTO.getCartId()).get();
        order.setCart(cart);
        order.setSize(order.getSize());
        return order;
    }
}
