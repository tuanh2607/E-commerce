package com.tuanh.ecommerce.service;

import org.springframework.stereotype.Service;

import com.tuanh.ecommerce.dto.request.CreateImageRequest;
import com.tuanh.ecommerce.entity.product.Image;
import com.tuanh.ecommerce.entity.product.Product;
import com.tuanh.ecommerce.enums.ErrorCode;
import com.tuanh.ecommerce.exception.AppException;
import com.tuanh.ecommerce.repository.ImageRepository;
import com.tuanh.ecommerce.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class ImageService {
    private final ImageRepository imageRepository;
    private final ProductRepository productRepository;

    public void createImage(CreateImageRequest request){
        Product product = productRepository.findById(request.getProductId()).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_EXIST));
        Image image = Image.builder()
                        .url(request.getUrl())
                        .product(product)
                        .build();
        imageRepository.save(image);
    }
}
