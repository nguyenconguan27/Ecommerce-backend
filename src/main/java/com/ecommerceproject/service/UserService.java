package com.ecommerceproject.service;

import com.ecommerceproject.dto.EmployeeDTO;
import com.ecommerceproject.dto.UserDTO;
import com.ecommerceproject.exception.DataNotFoundException;

public interface UserService {
     UserDTO getOneById(Integer id) throws DataNotFoundException;

     UserDTO updateUser(UserDTO userDTO);

     EmployeeDTO addEmployee();

}
