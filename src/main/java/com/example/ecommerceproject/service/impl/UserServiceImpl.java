package com.example.ecommerceproject.service.impl;

import com.example.ecommerceproject.converter.UserConverter;
import com.example.ecommerceproject.dto.EmployeeDTO;
import com.example.ecommerceproject.dto.UserDTO;
import com.example.ecommerceproject.exception.DataNotFoundException;
import com.example.ecommerceproject.repository.UserRepository;
import com.example.ecommerceproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;
    @Override
    public UserDTO getOneById(Integer id) throws DataNotFoundException {
        if(!userRepository.existsById(id)) {
            throw new DataNotFoundException("User not found");
        }
        return userConverter.toDTO(userRepository.findById(id).get());
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        return null;
    }

    @Override
    public EmployeeDTO addEmployee() {
        return null;
    }
}
