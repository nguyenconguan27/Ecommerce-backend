package com.example.ecommerceproject.apis;

import com.example.ecommerceproject.dto.ReceiptDTO;
import com.example.ecommerceproject.payload.request.BuyProductRequest;
import com.example.ecommerceproject.payload.response.ResponseObject;
import com.example.ecommerceproject.service.ReceiptService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/receipt")
public class ReceiptApis {
    @Autowired
    private ReceiptService receiptService;

    @PostMapping("/check-out")
    public ResponseEntity<ResponseObject> checkOut(@RequestBody @Valid BuyProductRequest buyProductRequest) throws Exception {
        ReceiptDTO receiptDTO = receiptService.checkoutProduct(buyProductRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ResponseObject.builder()
                        .message("Buy product successfully")
                        .httpStatus(HttpStatus.CREATED)
                        .data(receiptDTO)
                        .build()
        );
    }

    @PostMapping("/approved")
    public ResponseEntity<ResponseObject> approvedReceipt(@RequestParam("receipt_id") Integer receiptId, @RequestParam("action") Integer action) throws Exception {
        ReceiptDTO receiptDTO = receiptService.approveOrder(receiptId, action);
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseObject.builder()
                        .message("Approve receipt successfully")
                        .httpStatus(HttpStatus.OK)
                        .data(receiptDTO)
                        .build()
        );
    }

    @PutMapping("/received-confirm")
    public ResponseEntity<ResponseObject> updateOrder(@RequestParam("receipt_id") Integer receptId) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseObject.builder()
                        .httpStatus(HttpStatus.OK)
                        .message("Update status receipt successfully")
                        .data(receiptService.receivedProductConfirm(receptId))
                        .build()
        );
    }

    @GetMapping("/")
    public ResponseEntity<ResponseObject> getReceiptByStatus(@RequestParam("status") Integer status,  @RequestParam("user_id") Integer userId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseObject.builder()
                        .message("Get receipt successfully")
                        .httpStatus(HttpStatus.OK)
                        .data(receiptService.getReceipt(status, userId))
                        .build()
        );
    }
}
