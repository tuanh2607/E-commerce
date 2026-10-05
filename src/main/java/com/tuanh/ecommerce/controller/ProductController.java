package com.tuanh.ecommerce.controller;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tuanh.ecommerce.dto.request.CreateProductRequest;
import com.tuanh.ecommerce.dto.response.ProductResponse;
import com.tuanh.ecommerce.dto.response.Response;
import com.tuanh.ecommerce.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping 
    public ResponseEntity<Response<Void>> createProduct(@RequestBody CreateProductRequest request){
        productService.createProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(Response.<Void>builder()
                                                                .message("Create product successful")
                                                                .build());
    }

    @GetMapping
    public ResponseEntity<Response<List<ProductResponse>>> getProducts(@RequestParam(required = false) String keyword, @PageableDefault(size = 2, sort = "id") Pageable pageable){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(Response.<List<ProductResponse>>builder()
                                                            .result(productService.getProducts(keyword, pageable))
                                                            .message("Get products successful")
                                                            .build()
        );
    } 
    @GetMapping("/{id}")
    public ResponseEntity<Response<ProductResponse>> getProduct(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(
            Response.<ProductResponse>builder()
                        .result(productService.getProduct(id))
                        .message("Get product successful")
                        .build()
        );
    }
}
