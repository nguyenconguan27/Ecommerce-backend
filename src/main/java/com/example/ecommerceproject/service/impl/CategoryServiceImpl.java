package com.example.ecommerceproject.service.impl;

import com.example.ecommerceproject.converter.CategoryConverter;
import com.example.ecommerceproject.dto.CategoryDTO;
import com.example.ecommerceproject.entity.Category;
import com.example.ecommerceproject.repository.CategoryRepository;
import com.example.ecommerceproject.service.CategoryService;
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
