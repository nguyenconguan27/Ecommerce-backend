package com.ecommerceproject.apis;

import com.ecommerceproject.payload.request.UpdateOrderRequest;
import com.ecommerceproject.service.OrderService;
import com.ecommerceproject.exception.DataNotFoundException;
import com.ecommerceproject.payload.response.ResponseObject;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/order")
public class OrderApis {

    @Autowired
    private OrderService orderService;

    @PutMapping("/update")
    public ResponseEntity<ResponseObject> updateOrder(@Valid @RequestBody UpdateOrderRequest request) throws DataNotFoundException {
        return  ResponseEntity.status(HttpStatus.OK).body(
                ResponseObject.builder()
                        .message("Update order successfully")
                        .httpStatus(HttpStatus.OK)
                        .data(orderService.updateOrder(request))
                        .build()
        );
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseObject> deleteOrder(@RequestBody Integer[] orderId) throws DataNotFoundException {
        orderService.deleteOrder(orderId);
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseObject.builder()
                        .message("Delete order successfully")
                        .httpStatus(HttpStatus.OK)
                        .build()
        );
    }
}
