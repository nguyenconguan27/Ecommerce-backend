package com.example.ecommerceproject.payload.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AddReviewRequest {
    @NotNull
    private Integer rate;
    private String content;

}
