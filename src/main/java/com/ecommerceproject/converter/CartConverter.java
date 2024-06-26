package com.ecommerceproject.converter;

import com.ecommerceproject.dto.CartDTO;
import com.ecommerceproject.entity.Cart;
import com.ecommerceproject.repository.UserRepository;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Data
public class CartConverter {
    @Autowired
    private final ModelMapper modelMapper;
    @Autowired
    private final UserRepository userRepository;
    public CartDTO toDtO(Cart cart) {
        CartDTO cartDTO = modelMapper.map(cart, CartDTO.class);
        cartDTO.setUserId(cart.getCustomer().getId());
        return cartDTO;
    }
}
