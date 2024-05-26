package com.ecommerceproject.apis;

import com.ecommerceproject.payload.request.AddReviewRequest;
import com.ecommerceproject.payload.response.ResponseObject;
import com.ecommerceproject.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/review")
public class ReviewApis {

    @Autowired
    private ReviewService reviewService;
    @GetMapping("/")
    public ResponseEntity<ResponseObject> getByProduct(@RequestParam("productId") Integer productId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseObject.builder()
                        .message("Get review list successfully")
                        .httpStatus(HttpStatus.OK)
                        .data(reviewService.getByProduct(productId))
                        .build()
        );
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseObject> addReview(@RequestParam("userId") Integer userId,
                                                    @RequestParam("orderId") Integer orderId,
                                                    @RequestBody @Valid AddReviewRequest request) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ResponseObject.builder()
                        .httpStatus(HttpStatus.CREATED)
                        .message("Add review successfully")
                        .data(reviewService.addReview(request, orderId, userId))
                        .build()
        );
    }
}
