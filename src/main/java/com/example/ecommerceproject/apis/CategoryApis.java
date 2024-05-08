package com.example.ecommerceproject.apis;

import com.example.ecommerceproject.payload.response.ResponseObject;
import com.example.ecommerceproject.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class CategoryApis {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public ResponseEntity<ResponseObject> getCategory() {
        return ResponseEntity.ok().body(
                ResponseObject.builder()
                        .message("Get categories successfully")
                        .httpStatus(HttpStatus.OK)
                        .data(categoryService.getAllCategory())
                        .build()
        );
    }
}
