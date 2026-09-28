package com.tuanh.ecommerce.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tuanh.ecommerce.dto.request.CreateCategoryRequest;
import com.tuanh.ecommerce.dto.response.CategoryResponse;
import com.tuanh.ecommerce.dto.response.Response;
import com.tuanh.ecommerce.entity.product.Category;
import com.tuanh.ecommerce.enums.SuccessCode;
import com.tuanh.ecommerce.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Response<Void>> createCategory(@RequestBody CreateCategoryRequest request){
        categoryService.createCategory(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(Response.<Void>builder()
                            .code(SuccessCode.CREATE_CATEGORY.getCode())
                            .message(SuccessCode.CREATE_CATEGORY.getMessage())
                            .build());
    }     
    
    @GetMapping 
    public ResponseEntity<Response<List<CategoryResponse>>> getAllCategories(){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(Response.<List<CategoryResponse>>builder()
                                                                            .result(categoryService.getAllCategories())
                                                                            .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<Category>> getAllChildCategories(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(Response.<Category>builder()
                                                        .result(categoryService.getAllChildCategories(id))
                                                        .build());
    }
}
