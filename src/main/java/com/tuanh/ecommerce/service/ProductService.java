package com.tuanh.ecommerce.service;

import org.springframework.stereotype.Service;

import com.tuanh.ecommerce.dto.request.CreateProductRequest;
import com.tuanh.ecommerce.entity.product.Category;
import com.tuanh.ecommerce.entity.product.Product;
import com.tuanh.ecommerce.enums.ErrorCode;
import com.tuanh.ecommerce.enums.product.ProductStatus;
import com.tuanh.ecommerce.exception.AppException;
import com.tuanh.ecommerce.repository.CategoryRepository;
import com.tuanh.ecommerce.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public void createProduct(CreateProductRequest request){
        Category category = categoryRepository.findById(request.getCategory())
        .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_EXIST));
        Product product = Product.builder()
                                .name(request.getName())
                                .description(request.getDescription())
                                .price(request.getPrice())
                                .quantity(request.getQuantity())
                                .category(category)
                                .status(ProductStatus.ACTIVE)
                                .build();
        productRepository.save(product);
    }
}
