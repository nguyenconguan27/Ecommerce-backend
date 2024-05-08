package com.example.ecommerceproject.service.impl;

import com.example.ecommerceproject.converter.ReviewConverter;
import com.example.ecommerceproject.dto.ReviewDTO;
import com.example.ecommerceproject.entity.Customer;
import com.example.ecommerceproject.entity.Order;
import com.example.ecommerceproject.entity.Receipt;
import com.example.ecommerceproject.entity.Review;
import com.example.ecommerceproject.payload.request.AddReviewRequest;
import com.example.ecommerceproject.repository.CustomerRepository;
import com.example.ecommerceproject.repository.OrderRepository;
import com.example.ecommerceproject.repository.ReviewRepository;
import com.example.ecommerceproject.repository.UserRepository;
import com.example.ecommerceproject.service.ReviewService;
import com.example.ecommerceproject.util.Constants;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewConverter reviewConverter;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public List<ReviewDTO> getByProduct(Integer productId) {
        List<Review> reviewList = reviewRepository.getByProductId(productId);
        return reviewList.stream().map(review -> reviewConverter.toDTO(review)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ReviewDTO addReview(AddReviewRequest request, Integer orderId, Integer customerId) throws Exception {
        if(!orderRepository.existsById(orderId) || !customerRepository.existsById(customerId)) {
            throw new Exception("review is failed");
        }
        Order order = orderRepository.findById(orderId).get();
        order.setStatus(Constants.ORDER_STATUS.REVIEWED);
        Receipt receipt = order.getReceipt();
        if(receipt == null || receipt.getStatus() != Constants.RECEIPT_STATUS.RECEIVED) {
            throw new Exception("review is failed");
        }
        orderRepository.save(order);
        Customer customer = customerRepository.findById(customerId).get();
        Review review = new Review();
        review.setCustomer(customer);
        review.setOrder(order);
        review.setRate(request.getRate());
        review.setContent(request.getContent());
        Date date = new Date();
        review.setCommentDate(new java.sql.Date(date.getTime()));
        reviewRepository.save(review);
        return reviewConverter.toDTO(review);
    }
}
