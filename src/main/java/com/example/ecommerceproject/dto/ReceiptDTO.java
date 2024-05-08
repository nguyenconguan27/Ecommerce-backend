package com.example.ecommerceproject.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptDTO {
    private Integer id;
    private Date orderDate;
    private Integer status;
    private Integer userId;
    private List<OrderDTO> orderList;
    private Integer shippingId;
    private ShippingInfoDTO shippingInfoDTO;
}
