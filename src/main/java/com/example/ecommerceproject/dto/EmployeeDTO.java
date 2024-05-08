package com.example.ecommerceproject.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class EmployeeDTO extends UserDTO{
    private Integer salary;
}
