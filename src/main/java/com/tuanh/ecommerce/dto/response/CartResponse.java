package com.tuanh.ecommerce.dto.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
public class CartResponse {
    private UserResponse user;
    private List<CartItemResponse> items;
}
