package com.example.ecommerceproject.converter;

import com.example.ecommerceproject.dto.ShippingInfoDTO;
import com.example.ecommerceproject.entity.ShippingInfo;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Data
public class ShippingInfoConverter {

    public ShippingInfoDTO toDto(ShippingInfo shippingInfo) {
        ShippingInfoDTO shippingInfoDTO = new ShippingInfoDTO();
        shippingInfoDTO.setId(shippingInfo.getId());
        shippingInfoDTO.setNumber(shippingInfo.getNumber());
        shippingInfoDTO.setAddress(shippingInfo.getAddress());
        return shippingInfoDTO;
    }
}
