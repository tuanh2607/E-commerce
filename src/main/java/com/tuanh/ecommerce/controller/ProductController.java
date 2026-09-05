package com.tuanh.ecommerce.controller;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tuanh.ecommerce.dto.request.CreateProductRequest;
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
                                                                .build());
    }
}
