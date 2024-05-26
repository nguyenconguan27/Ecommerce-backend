package com.ecommerceproject.service;

import com.ecommerceproject.dto.ShippingInfoDTO;
import com.ecommerceproject.payload.request.ShippingInfoRequest;

import java.util.List;

public interface ShippingInfoService {
    ShippingInfoDTO addShippingInfo(ShippingInfoRequest shippingInfoRequest, Integer userId) throws Exception;

    List<ShippingInfoDTO> getByCustomer(Integer CustomerId);
}
