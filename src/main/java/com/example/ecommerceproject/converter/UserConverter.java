package com.example.ecommerceproject.converter;

import com.example.ecommerceproject.dto.UserDTO;
import com.example.ecommerceproject.entity.User;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Data
public class UserConverter {
    @Autowired
    private final ModelMapper modelMapper;

    public UserDTO toDTO(User user) {
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        userDTO.setRole(user.getRole().getRoleName());
        return userDTO;
    }

    public User toEntity(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        return user;
    }
}
