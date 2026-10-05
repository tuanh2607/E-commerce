package com.tuanh.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tuanh.ecommerce.dto.request.CreateProductRequest;
import com.tuanh.ecommerce.dto.response.ProductResponse;
import com.tuanh.ecommerce.entity.product.Category;
import com.tuanh.ecommerce.entity.product.Product;
import com.tuanh.ecommerce.enums.ErrorCode;
import com.tuanh.ecommerce.enums.product.ProductStatus;
import com.tuanh.ecommerce.exception.AppException;
import com.tuanh.ecommerce.mapper.ProductMapper;
import com.tuanh.ecommerce.repository.CategoryRepository;
import com.tuanh.ecommerce.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

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

    public List<ProductResponse> getProducts(String keyword, Pageable pageable){
        List<Product> products = ((keyword == null) || (keyword.isBlank())) ? productRepository.findAll(pageable).getContent() : productRepository.findByNameAndPagination(keyword, pageable);
        return productMapper.fromListProductToListProductResponse(products);
    }

    public ProductResponse getProduct(Long id){
        Product product = productRepository.findByIdWithDetails(id);
        return productMapper.fromProductToProductResponse(product);
    }
}
