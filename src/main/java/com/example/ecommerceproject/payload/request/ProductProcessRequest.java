package com.example.ecommerceproject.payload.request;

import com.example.ecommerceproject.dto.ImageDTO;
import com.example.ecommerceproject.dto.ProductSizeDTO;
import jakarta.validation.constraints.*;
import lombok.*;;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductProcessRequest {
    private Integer id;
    @NotNull(message = "Expense is mandatory")
    private Integer expense;
    @NotBlank(message = "Description is mandatory")
    private String des;
    @Positive(message = "Price must be a positive number")
    @NotNull(message = "Price is mandatory")
    private Integer price;
    @NotBlank(message = "title is mandatory")
    private String title;
    @NotNull(message = "Category is mandatory")
    private Integer categoryId;
    @Positive(message = "Price must be a positive number")
    private Integer prePrice;
    private List<ImageDTO> imageList;
    @NotNull(message = "Size list is mandatory")
    private List<ProductSizeDTO> sizeList;
}
