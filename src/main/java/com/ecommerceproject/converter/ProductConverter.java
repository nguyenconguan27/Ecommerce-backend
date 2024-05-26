package com.ecommerceproject.converter;

import com.ecommerceproject.dto.ImageDTO;
import com.ecommerceproject.dto.ProductDTO;
import com.ecommerceproject.dto.ProductSizeDTO;
import com.ecommerceproject.entity.Product;
import com.ecommerceproject.repository.CategoryRepository;
import com.ecommerceproject.repository.ProductRepository;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Data
public class ProductConverter {
    @Autowired
    private final ModelMapper modelMapper;

    @Autowired
    private final ProductRepository productRepository;

    @Autowired
    private final CategoryRepository categoryRepository;
    @Autowired
    private final CategoryConverter categoryConverter;

    public ProductDTO toDTO(Product product) {
        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
        productDTO.setCategory(categoryConverter.toDto(product.getCategory()));
        List<ImageDTO> imageList = productDTO.getImageList().stream().map(image -> new ImageDTO(image.getFileName())).collect(Collectors.toList());
        productDTO.setSizeList(product.getProductSizeList().stream().map(productSize -> new ProductSizeDTO(productSize.getId().getSize(), productSize.getQuantity())).collect(Collectors.toList()));
        productDTO.setImageList(imageList);
        return productDTO;
    }

//    public Product toEntity(ProductDTO productDTO) {
//        Product product = modelMapper.map(productDTO, Product.class);
//        Category category = categoryRepository.findById(productDTO.getCategoryId()).get();
//        product.setCategory(category);
//        return product;
//    }


}
