package com.ecommerceproject.service.impl;

import com.ecommerceproject.dto.EmployeeDTO;
import com.ecommerceproject.dto.UserDTO;
import com.ecommerceproject.exception.DataNotFoundException;
import com.ecommerceproject.converter.UserConverter;
import com.ecommerceproject.repository.UserRepository;
import com.ecommerceproject.service.UserService;
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
