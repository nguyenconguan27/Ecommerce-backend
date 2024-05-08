package com.example.ecommerceproject.service;

import com.example.ecommerceproject.dto.ShippingInfoDTO;
import com.example.ecommerceproject.entity.ShippingInfo;
import com.example.ecommerceproject.payload.request.ShippingInfoRequest;

import java.util.List;

public interface ShippingInfoService {
    ShippingInfoDTO addShippingInfo(ShippingInfoRequest shippingInfoRequest, Integer userId) throws Exception;

    List<ShippingInfoDTO> getByCustomer(Integer CustomerId);
}
