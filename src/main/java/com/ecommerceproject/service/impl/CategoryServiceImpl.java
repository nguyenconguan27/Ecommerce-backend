package com.ecommerceproject.service.impl;

import com.ecommerceproject.converter.CategoryConverter;
import com.ecommerceproject.dto.CategoryDTO;
import com.ecommerceproject.repository.CategoryRepository;
import com.ecommerceproject.service.CategoryService;
import com.ecommerceproject.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryConverter categoryConverter;
    @Override
    public List<CategoryDTO> getAllCategory() {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList.stream().map(category -> categoryConverter.toDto(category)).collect(Collectors.toList());
    }
}
