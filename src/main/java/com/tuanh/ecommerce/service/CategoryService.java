package com.tuanh.ecommerce.service;

import org.springframework.stereotype.Service;

import com.tuanh.ecommerce.dto.request.CreateCategoryRequest;
import com.tuanh.ecommerce.entity.product.Category;
import com.tuanh.ecommerce.enums.ErrorCode;
import com.tuanh.ecommerce.exception.AppException;
import com.tuanh.ecommerce.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public void createCategory(CreateCategoryRequest request){
        Category pCategory = null;
        if(request.getParentCategory() != null){
            pCategory = categoryRepository.findById(request.getParentCategory()).orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_EXIST));
        }
        Category category = Category.builder()
                                .name(request.getName())
                                .parentCategory(pCategory)
                                .build();
        categoryRepository.save(category);
    }
}
