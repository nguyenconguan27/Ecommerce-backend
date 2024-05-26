package com.ecommerceproject.service.impl;


import com.ecommerceproject.converter.ProductConverter;
import com.ecommerceproject.dto.ImageDTO;
import com.ecommerceproject.dto.ProductDTO;
import com.ecommerceproject.dto.ProductSizeDTO;
import com.ecommerceproject.entity.Image;
import com.ecommerceproject.entity.Product;
import com.ecommerceproject.entity.ProductSize;
import com.ecommerceproject.entity.ProductSizeKey;
import com.ecommerceproject.exception.DataConfictException;
import com.ecommerceproject.exception.DataNotFoundException;
import com.ecommerceproject.payload.request.ProductProcessRequest;
import com.ecommerceproject.payload.response.ProductListResponse;
import com.ecommerceproject.repository.*;
import com.ecommerceproject.service.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductConverter productConverter;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductSizeRepository productSizeRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ImageRepository imageRepository;

    @Override
    public ProductDTO getOne(Integer id) throws Exception {
        if (!productRepository.existsById(id)) {
            throw new DataNotFoundException("Product is not found");
        }
        ProductDTO productDTO = productConverter.toDTO(productRepository.findById(id).get());
        Double rate = reviewRepository.getAverageRate(productDTO.getId());
        if(rate == null) {
            productDTO.setRate(0.0);
        }
        else {
            productDTO.setRate(rate);
        }
        return productDTO;
    }

    @Override
    @Transactional
    public ProductDTO update(ProductProcessRequest processRequest) throws Exception {
        if (!productRepository.existsById(processRequest.getId())) {
            throw new Exception("Update product failed");
        }
        if (!categoryRepository.existsById(processRequest.getCategoryId())) {
            Map<String, String> error = new HashMap<>();
            error.put("prePrice", "Price before discount must be smaller than price");
            throw new DataConfictException("Update product failed", error);
        }
        Product product = productRepository.findById(processRequest.getId()).get();
        product.setDes(processRequest.getDes());
        product.setTitle(processRequest.getTitle());
        product.setCategory(categoryRepository.findById(processRequest.getCategoryId()).get());
        product.setPrice(processRequest.getPrice());
        product.setPrePrice(processRequest.getPrePrice());
        if (processRequest.getImageList() != null && processRequest.getImageList().size() > 0) {
            for(ImageDTO imageDTO : processRequest.getImageList()) {
                Image image = new Image(imageDTO.getFileName(), product);
                imageRepository.save(image);
            }
        }
        for (ProductSizeDTO productSizeDTO : processRequest.getSizeList()) {
            ProductSizeKey productSizeKey = new ProductSizeKey(processRequest.getId(), productSizeDTO.getSize());
            ProductSize productSize = new ProductSize(productSizeKey, product, productSizeDTO.getQuantity());
            productSizeRepository.save(productSize);
        }
        productRepository.save(product);
        return productConverter.toDTO(product);
    }


    @Override
    @Transactional
    public void delete(Integer id) throws Exception {
        if (!productRepository.existsById(id)) {
            throw new Exception("Delete product fail");
        }
        Product product = productRepository.findById(id).get();
        productRepository.delete(product);
    }

    @Override
    public ProductListResponse getByOptions(String title, Integer categoryId, Integer minPrice, Integer maxPrice, Pageable pageable) {
        Page<Product> productPage;
        if (categoryId == 0) {
            productPage = productRepository.findByNoneCate(minPrice, maxPrice, title, pageable);
        } else {
            productPage = productRepository.findByALlOptions(minPrice, maxPrice, categoryId, title, pageable);
        }
        List<Product> productList = productPage.getContent();
        List<ProductDTO> productDTOList = productList.stream().map(element -> productConverter.toDTO(element)).collect(Collectors.toList());
        for(ProductDTO productDTO: productDTOList) {
            Double rate = reviewRepository.getAverageRate(productDTO.getId());
            if(rate == null) {
                productDTO.setRate(0.0);
            }
            else {
                productDTO.setRate(rate);
            }
        }
        return new ProductListResponse(productDTOList, productPage.getTotalPages());
    }

    @Override
    @Transactional
    public ProductDTO add(ProductProcessRequest processRequest) throws Exception {
        if (!categoryRepository.existsById(processRequest.getCategoryId())) {
            throw new Exception("Add product failed");
        }
        if (processRequest.getPrePrice() <= processRequest.getPrice()) {
            throw new Exception("Add product failed");
        }
        Product product = new Product();
        product.setDes(processRequest.getDes());
        product.setPrice(processRequest.getPrice());
        product.setTitle(processRequest.getTitle());
        product.setPrePrice(processRequest.getPrePrice());
        product.setCategory(categoryRepository.findById(processRequest.getCategoryId()).get());
        product.setSold(0);
        Date date = new Date();
        product.setReleaseDate(new java.sql.Date(date.getTime()));
        product = productRepository.save(product);
        List<ProductSize> productSizeList = new ArrayList<>();
        for (ProductSizeDTO productSizeDTO : processRequest.getSizeList()) {
            ProductSize productSize = new ProductSize(new ProductSizeKey(product.getId(), productSizeDTO.getSize()), product, productSizeDTO.getQuantity());
            productSizeRepository.save(productSize);
            productSizeList.add(productSize);
        }
        product.setProductSizeList(productSizeList);
        List<Image> imageList = new ArrayList<>();
        for(ImageDTO imageDTO : processRequest.getImageList()) {
            Image image = new Image(imageDTO.getFileName(), product);
            imageRepository.save(image);
            imageList.add(image);
        }
        product.setImageList(imageList);
        return productConverter.toDTO(product);
    }
}
