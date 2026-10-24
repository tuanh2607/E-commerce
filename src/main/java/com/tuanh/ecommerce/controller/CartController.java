package com.tuanh.ecommerce.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tuanh.ecommerce.dto.request.AddToCartRequest;
import com.tuanh.ecommerce.dto.response.CartResponse;
import com.tuanh.ecommerce.dto.response.Response;
import com.tuanh.ecommerce.service.CartService;

import lombok.RequiredArgsConstructor;

@RestController 
@RequiredArgsConstructor 
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    @PostMapping
    public ResponseEntity<Response<Void>> addToCart(@RequestBody AddToCartRequest request){
        cartService.addToCart(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                        Response.<Void>builder()
                                .message("Add to cart successful")
                                .build()
        );
    } 

    @GetMapping("/{id}")
    public ResponseEntity<Response<CartResponse>> getCart(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                                Response.<CartResponse>builder()
                                    .message("Get cart successful")
                                    .result(cartService.getItemsInCart(id))
                                    .build()
        );
    }
}
