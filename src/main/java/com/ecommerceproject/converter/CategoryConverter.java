package com.ecommerceproject.converter;

import com.ecommerceproject.dto.CategoryDTO;
import com.ecommerceproject.entity.Category;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
@Component
public class CategoryConverter {
    @Autowired
    private final ModelMapper modelMapper;

    public CategoryDTO toDto(Category category) {
        CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);
        return categoryDTO;
    }

    public Category toEntity(CategoryDTO categoryDTO) {
        Category category = modelMapper.map(categoryDTO, Category.class);
        return category;
    }
}
