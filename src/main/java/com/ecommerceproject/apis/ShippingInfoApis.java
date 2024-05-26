package com.ecommerceproject.apis;

import com.ecommerceproject.payload.request.ShippingInfoRequest;
import com.ecommerceproject.payload.response.ResponseObject;
import com.ecommerceproject.service.ShippingInfoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/shipping-info")
public class ShippingInfoApis {

    @Autowired
    private ShippingInfoService shippingInfoService;

    @PostMapping("/add")
    private ResponseEntity<ResponseObject> addShippingInfo(@Valid @RequestBody ShippingInfoRequest shippingInfoRequest, @RequestParam("cus_id") Integer userId) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ResponseObject.builder()
                        .message("Create shipping info successfully")
                        .httpStatus(HttpStatus.CREATED)
                        .data(shippingInfoService.addShippingInfo(shippingInfoRequest, userId))
                        .build()
        );
    }

    @GetMapping("/")
    private ResponseEntity<ResponseObject> getByCustomer(@RequestParam("cus_id") Integer customerId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseObject.builder()
                        .message("get shipping info by customer successfully")
                        .httpStatus(HttpStatus.OK)
                        .data(shippingInfoService.getByCustomer(customerId))
                        .build()
        );
    }
}
