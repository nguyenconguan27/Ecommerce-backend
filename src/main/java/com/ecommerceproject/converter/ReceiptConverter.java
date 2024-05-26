package com.ecommerceproject.converter;

import com.ecommerceproject.dto.ReceiptDTO;
import com.ecommerceproject.entity.Order;
import com.ecommerceproject.entity.User;
import com.ecommerceproject.repository.UserRepository;
import com.ecommerceproject.entity.Receipt;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@Data
public class ReceiptConverter {
    @Autowired
    private final ModelMapper modelMapper;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final OrderConverter orderConverter;
    @Autowired
    private final ShippingInfoConverter shippingInfoConverter;


    public ReceiptDTO toDTO(Receipt receipt) {
        ReceiptDTO receiptDTO = modelMapper.map(receipt, ReceiptDTO.class);
        receiptDTO.setUsername(receipt.getCustomer().getUsername());
        receiptDTO.setOrderList(receipt.getOrderList().stream().map(order -> orderConverter.toDTO(order)).collect(Collectors.toList()));
        if(receipt.getShipping() != null) {
            receiptDTO.setShippingId(receipt.getShipping().getId());
        }
        Integer total = 0;
        for(Order order: receipt.getOrderList()) {
            total += order.getQuantity() * order.getPrice();
        }
        receiptDTO.setTotal(total);
        receiptDTO.setShippingInfoDTO(shippingInfoConverter.toDto(receipt.getShippingInfo()));
        return receiptDTO;
    }

}
