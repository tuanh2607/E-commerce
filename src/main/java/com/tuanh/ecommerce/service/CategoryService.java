package com.tuanh.ecommerce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tuanh.ecommerce.dto.request.CreateCategoryRequest;
import com.tuanh.ecommerce.dto.response.CategoryResponse;
import com.tuanh.ecommerce.entity.product.Category;
import com.tuanh.ecommerce.enums.ErrorCode;
import com.tuanh.ecommerce.exception.AppException;
import com.tuanh.ecommerce.mapper.CategoryMapper;
import com.tuanh.ecommerce.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public void createCategory(CreateCategoryRequest request){
        Category parentCategory = null;
        if(request.getParentCategory() != null){
            parentCategory = categoryRepository.findById(request.getParentCategory()).orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_EXIST));
        }
        Category category = Category.builder()
                                .name(request.getName())
                                .parentCategory(parentCategory)
                                .build();
        categoryRepository.save(category);
    }

    public List<CategoryResponse> getAllCategories(){
        List<Category> list = categoryRepository.findAll();
        return categoryMapper.fromCategoryToCategoryResponseList(list);
    }

    public CategoryResponse getAllChildCategories(Long id){
        Category parentCategory = categoryRepository.findByIdWithChildren(id).orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_EXIST));
        return CategoryResponse.builder()
            .id(parentCategory.getId())
            .name(parentCategory.getName())
            .parentId(parentCategory.getParentCategory() != null ? parentCategory.getParentCategory().getId() : null)
            .childrens(parentCategory.getChildrens().stream()
                .map(child -> CategoryResponse.builder()
                    .id(child.getId())
                    .name(child.getName())
                    .parentId(parentCategory.getId())
                .build()
                ).toList()
            )
        .build();
    }
}
