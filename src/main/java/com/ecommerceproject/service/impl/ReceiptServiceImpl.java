package com.ecommerceproject.service.impl;

import com.ecommerceproject.dto.ReceiptDTO;
import com.ecommerceproject.entity.*;
import com.ecommerceproject.payload.request.BuyProductRequest;
import com.ecommerceproject.repository.*;
import com.ecommerceproject.util.Constants;
import com.ecommerceproject.converter.ReceiptConverter;
import com.ecommerceproject.service.ReceiptService;
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
    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional
    public ReceiptDTO approveOrder(Integer receiptId, Integer action) throws Exception {
        if (!receiptRepository.existsById(receiptId) || (action != Constants.ADMIN_APPROVAL_ACTION.ACCEPT && action != Constants.ADMIN_APPROVAL_ACTION.REFUSE)) {
            throw new Exception("Approve order failed");
        }
        Receipt receipt = receiptRepository.findById(receiptId).get();
        Notification notification = new Notification();
        notification.setStatus(Constants.NOTIFY_STATUS.unread);
        notification.setUser(receipt.getCustomer());
        notification.setDate(new java.sql.Date(new Date().getTime()));
        if (action == 1) {
            receipt.setStatus(Constants.RECEIPT_STATUS.INSHIPPING);
            receipt = receiptRepository.save(receipt);
            Shipping shipping = new Shipping();
            shipping.setReceipt(receipt);
            shipping.setShippingDate(new java.sql.Date(new Date().getTime()));
            shippingRepository.save(shipping);
            receipt.setShipping(shipping);
            notification.setContent(String.format("Đơn hàng %s của bạn đang được giao.", "HD" + receipt.getId()));
        }
        else {
            receipt.setStatus(Constants.RECEIPT_STATUS.CANCEL);
            receiptRepository.save(receipt);
            notification.setContent(String.format("Đơn hàng %s của bạn đã bị huỷ.", "HD" + receipt.getId()));
        }
        notificationRepository.save(notification);
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
            Product product = order.getProduct();
            product.setSold(product.getSold() + order.getQuantity());
            productRepository.save(product);
            orderList.add(order);
        }
        if (!shippingInfoRepository.existsById(buyProductRequest.getShippingInfo())) {
            throw new Exception("Please add shipping information to checkout");
        }
        ShippingInfo shippingInfo = shippingInfoRepository.findById(buyProductRequest.getShippingInfo()).get();
        Customer customer = shippingInfo.getCustomer();
        receipt.setShippingInfo(shippingInfo);
        receipt.setOrderList(orderList);
        receipt.setPayMethod(buyProductRequest.getPayMethod());
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

    @Override
    public List<ReceiptDTO> getAllReceipt() {
        List<Receipt> receiptList = receiptRepository.findAll();
        List<ReceiptDTO> receiptDTOList = receiptList.stream().map(receipt -> receiptConverter.toDTO(receipt)).collect(Collectors.toList());
        return receiptDTOList;
    }


}
