package com.example.ecommerceproject.service.impl;

import com.example.ecommerceproject.converter.ShippingInfoConverter;
import com.example.ecommerceproject.dto.ShippingInfoDTO;
import com.example.ecommerceproject.entity.*;
import com.example.ecommerceproject.payload.request.ShippingInfoRequest;
import com.example.ecommerceproject.repository.*;
import com.example.ecommerceproject.service.ShippingInfoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShippingInfoServiceImpl implements ShippingInfoService {
    @Autowired
    private ShippingInfoRepository shippingInfoRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ShippingInfoConverter shippingInfoConverter;


    @Override
    @Transactional
    public ShippingInfoDTO addShippingInfo(ShippingInfoRequest shippingInfoRequest, Integer userId) throws Exception {

        if(!customerRepository.existsById(userId)) {
            throw new Exception("Add shipping info fail");
        }
        ShippingInfo shippingInfo = new ShippingInfo();
        shippingInfo.setNumber(shippingInfoRequest.getNumber());
        shippingInfo.setAddress(shippingInfoRequest.getAddress());
        Customer user = customerRepository.findById(userId).get();
        shippingInfo.setCustomer(user);
        shippingInfo = shippingInfoRepository.save(shippingInfo);
        return shippingInfoConverter.toDto(shippingInfo);
    }

    @Override
    public List<ShippingInfoDTO> getByCustomer(Integer customerId) {
        List<ShippingInfo> shippingInfoList = shippingInfoRepository.findByCustomer_id(customerId);
        return shippingInfoList.stream().map(shippingInfo -> shippingInfoConverter.toDto(shippingInfo)).collect(Collectors.toList());
    }


}
