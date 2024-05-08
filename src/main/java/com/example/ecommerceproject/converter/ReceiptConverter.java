package com.example.ecommerceproject.converter;

import com.example.ecommerceproject.dto.ReceiptDTO;
import com.example.ecommerceproject.entity.Receipt;
import com.example.ecommerceproject.entity.User;
import com.example.ecommerceproject.repository.UserRepository;
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
        receiptDTO.setUserId(receipt.getCustomer().getId());
        receiptDTO.setOrderList(receipt.getOrderList().stream().map(order -> orderConverter.toDTO(order)).collect(Collectors.toList()));
        if(receipt.getShipping() != null) {
            receiptDTO.setShippingId(receipt.getShipping().getId());
        }
        receiptDTO.setShippingInfoDTO(shippingInfoConverter.toDto(receipt.getShippingInfo()));
        return receiptDTO;
    }

    public Receipt toEntity(ReceiptDTO receiptDTO) {
        Receipt receipt = modelMapper.map(receiptDTO, Receipt.class);
        User user = userRepository.findById(receiptDTO.getUserId()).get();
        return receipt;
    }
}
