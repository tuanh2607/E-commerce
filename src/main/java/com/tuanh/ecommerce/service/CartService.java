package com.tuanh.ecommerce.service;

import org.springframework.stereotype.Service;

import com.tuanh.ecommerce.dto.request.CreateCartRequest;
import com.tuanh.ecommerce.dto.response.CartResponse;
import com.tuanh.ecommerce.entity.cart_order.Cart;
import com.tuanh.ecommerce.entity.user.User;
import com.tuanh.ecommerce.enums.ErrorCode;
import com.tuanh.ecommerce.exception.AppException;
import com.tuanh.ecommerce.mapper.CartMapper;
import com.tuanh.ecommerce.repository.CartRepository;
import com.tuanh.ecommerce.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final CartMapper cartMapper;

    public void createCart(CreateCartRequest request){
        User user = userRepository.findById(request.getId()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXIST));
        Cart cart = Cart.builder()
                        .user(user)
                        .build();
        cartRepository.save(cart);
    }

    public CartResponse getItemsInCart(Long id) {
        Cart cart = cartRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.CART_NOT_EXIST));
        return cartMapper.fromCartToCartResponse(cart);
    }
}
