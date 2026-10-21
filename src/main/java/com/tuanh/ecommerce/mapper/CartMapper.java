package com.tuanh.ecommerce.mapper;

import org.mapstruct.Mapper;

import com.tuanh.ecommerce.dto.response.CartItemResponse;
import com.tuanh.ecommerce.dto.response.CartResponse;
import com.tuanh.ecommerce.entity.cart_order.Cart;
import com.tuanh.ecommerce.entity.cart_order.CartItem;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface CartMapper {
    CartResponse fromCartToCartResponse(Cart cart);
    CartItemResponse fromCartItemToCartItemResponse(CartItem item);
}
