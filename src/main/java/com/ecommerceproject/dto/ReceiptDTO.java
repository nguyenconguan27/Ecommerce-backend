package com.ecommerceproject.dto;

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
    private String username;
    private List<OrderDTO> orderList;
    private Integer total;
    private Integer shippingId;
    private ShippingInfoDTO shippingInfoDTO;
}
