package com.example.ecommerceproject.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
    private Integer id;
    private Date commentDate;
    private String content;
    private Double rate;
    private Integer productId;
    private String userImage;
    private String username;
}
