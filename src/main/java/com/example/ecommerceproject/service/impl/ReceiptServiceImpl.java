package com.example.ecommerceproject.service.impl;

import com.example.ecommerceproject.converter.ReceiptConverter;
import com.example.ecommerceproject.dto.OrderDTO;
import com.example.ecommerceproject.dto.ReceiptDTO;
import com.example.ecommerceproject.entity.*;
import com.example.ecommerceproject.payload.request.ApproveOrderRequest;
import com.example.ecommerceproject.payload.request.BuyProductRequest;
import com.example.ecommerceproject.repository.*;
import com.example.ecommerceproject.service.ReceiptService;
import com.example.ecommerceproject.util.Constants;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReceiptServiceImpl implements ReceiptService {
    @Autowired
    private ReceiptRepository receiptRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ShippingInfoRepository shippingInfoRepository;
    @Autowired
    private ReceiptConverter receiptConverter;
    @Autowired
    private ShippingRepository shippingRepository;
    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    @Transactional
    public ReceiptDTO approveOrder(Integer receiptId, Integer action) throws Exception {
        if (!receiptRepository.existsById(receiptId) || (action != Constants.ADMIN_APPROVAL_ACTION.ACCEPT && action != Constants.ADMIN_APPROVAL_ACTION.REFUSE)) {
            throw new Exception("Approve order failed");
        }
        Receipt receipt = receiptRepository.findById(receiptId).get();
        if (action == 1) {
            receipt.setStatus(Constants.RECEIPT_STATUS.INSHIPPING);
            receipt = receiptRepository.save(receipt);
            Shipping shipping = new Shipping();
            shipping.setReceipt(receipt);
            shipping.setShippingDate(new java.sql.Date(new Date().getTime()));
            shippingRepository.save(shipping);
            receipt.setShipping(shipping);
        }
        else {
            receipt.setStatus(Constants.RECEIPT_STATUS.CANCEL);
            receiptRepository.save(receipt);
        }
        return receiptConverter.toDTO(receipt);
    }

    @Override
    @Transactional
    public ReceiptDTO checkoutProduct(BuyProductRequest buyProductRequest) throws Exception {
        Receipt receipt = new Receipt();
        List<Order> orderList = new ArrayList<>();
        for (Integer orderId : buyProductRequest.getOrderIdList()) {
            if (!orderRepository.existsById(orderId)) {
                throw new Exception("Please add a order to checkout");
            }
            Order order = orderRepository.findById(orderId).get();
            if(order.getStatus() != Constants.ORDER_STATUS.INCART) {
                throw new Exception();
            }
            order.setStatus(Constants.ORDER_STATUS.CHECKEDOUT);
            orderRepository.save(order);
            orderList.add(order);
        }
        if (!shippingInfoRepository.existsById(buyProductRequest.getShippingInfo())) {
            throw new Exception("Please add shipping information to checkout");
        }
        ShippingInfo shippingInfo = shippingInfoRepository.findById(buyProductRequest.getShippingInfo()).get();
        Customer customer = shippingInfo.getCustomer();
        receipt.setShippingInfo(shippingInfo);
        receipt.setOrderList(orderList);
        Date date = new Date();
        receipt.setOrderDate((new java.sql.Date(date.getTime())));
        receipt.setCustomer(customer);
        receipt.setStatus(Constants.RECEIPT_STATUS.CHECKEDOUT);
        receiptRepository.save(receipt);
        for (Order order : orderList) {
            order.setReceipt(receipt);
            orderRepository.save(order);
        }
        return receiptConverter.toDTO(receipt);
    }

    @Override
    @Transactional
    public ReceiptDTO receivedProductConfirm(Integer receiptId) throws Exception {
        if(!receiptRepository.existsById(receiptId)) {
            throw new Exception("confirm received receipt failed");
        }
        Receipt receipt = receiptRepository.findById(receiptId).get();
        receipt.setStatus(Constants.RECEIPT_STATUS.RECEIVED);
        receiptRepository.save(receipt);
        return receiptConverter.toDTO(receipt);
    }

    @Override
    public List<ReceiptDTO> getReceipt(Integer status, Integer userId) {
        List<Receipt> receiptList = receiptRepository.findByStatusAndCustomer_id(status, userId);
        List<ReceiptDTO> receiptDTOList = receiptList.stream().map(receipt -> receiptConverter.toDTO(receipt)).collect(Collectors.toList());
        return receiptDTOList;
    }


}
