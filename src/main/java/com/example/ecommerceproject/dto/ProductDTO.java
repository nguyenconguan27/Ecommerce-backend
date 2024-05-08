package com.example.ecommerceproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Integer id;
    private String des;
    private Integer price;
    private Integer prePrice;
    private String title;
    private Integer sold;
    private Integer categoryId;
    private List<ImageDTO> imageList;
    private List<ProductSizeDTO> sizeList;
    private Double rate;
    private Date releaseDate;
}
