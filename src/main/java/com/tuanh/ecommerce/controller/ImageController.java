package com.tuanh.ecommerce.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tuanh.ecommerce.dto.request.CreateImageRequest;
import com.tuanh.ecommerce.dto.response.Response;
import com.tuanh.ecommerce.service.ImageService;

import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/images")
@RequiredArgsConstructor 
public class ImageController {
    private final ImageService imageService;

    @PostMapping
    public ResponseEntity<Response<Void>> createImage(@RequestBody CreateImageRequest request){
        imageService.createImage(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
            Response.<Void> builder()
            .message("Create image successful")
            .build()
        );
    }
}
