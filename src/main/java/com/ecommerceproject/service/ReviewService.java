package com.ecommerceproject.service;

import com.ecommerceproject.dto.ReviewDTO;
import com.ecommerceproject.payload.request.AddReviewRequest;

import java.util.List;

public interface ReviewService {
    List<ReviewDTO> getByProduct(Integer productId);

    ReviewDTO addReview(AddReviewRequest request, Integer orderId, Integer customerId) throws Exception;
}
