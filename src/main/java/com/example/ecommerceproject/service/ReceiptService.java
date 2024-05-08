package com.example.ecommerceproject.service;

import com.example.ecommerceproject.dto.ReceiptDTO;
import com.example.ecommerceproject.payload.request.ApproveOrderRequest;
import com.example.ecommerceproject.payload.request.BuyProductRequest;

import java.util.ArrayList;
import java.util.List;

public interface ReceiptService {
     ReceiptDTO approveOrder(Integer receiptId, Integer action) throws Exception;

     ReceiptDTO checkoutProduct(BuyProductRequest buyProductRequest) throws Exception;

     ReceiptDTO receivedProductConfirm(Integer receiptId) throws Exception;

     List<ReceiptDTO> getReceipt(Integer status, Integer userId);
}
