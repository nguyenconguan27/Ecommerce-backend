package com.ecommerceproject.converter;

import com.ecommerceproject.dto.CustomerDTO;
import com.ecommerceproject.dto.ReceiptDTO;
import com.ecommerceproject.entity.Customer;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Data
public class CustomerConverter {
    @Autowired
    private final ModelMapper modelMapper;
    public CustomerDTO toDTO(Customer customer) {
        CustomerDTO customerDTO =  modelMapper.map(customer, CustomerDTO.class);
        return customerDTO;
    }
}
