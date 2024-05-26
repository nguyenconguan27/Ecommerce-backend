package com.ecommerceproject.service;

import com.ecommerceproject.dto.ReceiptDTO;
import com.ecommerceproject.payload.request.BuyProductRequest;

import java.util.List;

public interface ReceiptService {
     ReceiptDTO approveOrder(Integer receiptId, Integer action) throws Exception;

     ReceiptDTO checkoutProduct(BuyProductRequest buyProductRequest) throws Exception;

     ReceiptDTO receivedProductConfirm(Integer receiptId) throws Exception;

     List<ReceiptDTO> getReceipt(Integer status, Integer userId);
     List<ReceiptDTO> getAllReceipt();
}
