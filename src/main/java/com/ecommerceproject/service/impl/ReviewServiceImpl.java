package com.ecommerceproject.service.impl;

import com.ecommerceproject.converter.ReviewConverter;
import com.ecommerceproject.dto.ReviewDTO;
import com.ecommerceproject.entity.Customer;
import com.ecommerceproject.entity.Order;
import com.ecommerceproject.entity.Receipt;
import com.ecommerceproject.entity.Review;
import com.ecommerceproject.payload.request.AddReviewRequest;
import com.ecommerceproject.repository.CustomerRepository;
import com.ecommerceproject.repository.OrderRepository;
import com.ecommerceproject.repository.ReviewRepository;
import com.ecommerceproject.service.ReviewService;
import com.ecommerceproject.util.Constants;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
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
