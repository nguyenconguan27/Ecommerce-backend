package com.ecommerceproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO extends UserDTO{
    private Integer cartId;

    public CustomerDTO(Integer id, String email, String number,String password, String username, String role, String image, Integer cartId) {
        super(id, email, number,password, username, role, image);
        this.cartId = cartId;
    }
}
