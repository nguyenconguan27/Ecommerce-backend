package com.example.ecommerceproject.service;

import com.example.ecommerceproject.dto.EmployeeDTO;
import com.example.ecommerceproject.dto.UserDTO;
import com.example.ecommerceproject.entity.Employee;
import com.example.ecommerceproject.exception.DataNotFoundException;
import org.springframework.stereotype.Service;

public interface UserService {
     UserDTO getOneById(Integer id) throws DataNotFoundException;

     UserDTO updateUser(UserDTO userDTO);

     EmployeeDTO addEmployee();
}
