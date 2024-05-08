package com.example.ecommerceproject.converter;

import com.example.ecommerceproject.dto.ReviewDTO;
import com.example.ecommerceproject.entity.Review;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Data
public class ReviewConverter {
    @Autowired
    private final ModelMapper modelMapper;

    public ReviewDTO toDTO(Review review) {
        ReviewDTO reviewDTO = modelMapper.map(review, ReviewDTO.class);
        reviewDTO.setProductId(review.getOrder().getProduct().getId());
        reviewDTO.setUsername(review.getCustomer().getUsername());
        reviewDTO.setUserImage(review.getCustomer().getImage());
        return reviewDTO;
    }
}
