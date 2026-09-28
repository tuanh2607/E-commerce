package com.tuanh.ecommerce.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.tuanh.ecommerce.dto.response.CategoryResponse;
import com.tuanh.ecommerce.entity.product.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(source = "parentCategory.id", target = "parentCategory")
    CategoryResponse fromCategoryToCategoryResponse(Category category);

    List<CategoryResponse> fromCategoryToCategoryResponseList(List<Category> list);
}
