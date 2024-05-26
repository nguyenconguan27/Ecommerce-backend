package com.ecommerceproject.converter;

import com.ecommerceproject.dto.ShippingInfoDTO;
import com.ecommerceproject.entity.ShippingInfo;
import lombok.Data;
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
