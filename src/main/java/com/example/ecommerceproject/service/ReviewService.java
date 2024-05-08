package com.example.ecommerceproject.service;

import com.example.ecommerceproject.dto.ReviewDTO;
import com.example.ecommerceproject.payload.request.AddReviewRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public interface ReviewService {
    List<ReviewDTO> getByProduct(Integer productId);

    ReviewDTO addReview(AddReviewRequest request, Integer orderId, Integer customerId) throws Exception;
}
