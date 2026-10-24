package com.tuanh.ecommerce.service;

import org.springframework.stereotype.Service;

import com.tuanh.ecommerce.dto.request.AddToCartRequest;
import com.tuanh.ecommerce.dto.response.CartResponse;
import com.tuanh.ecommerce.entity.cart_order.Cart;
import com.tuanh.ecommerce.entity.cart_order.CartProduct;
import com.tuanh.ecommerce.entity.product.Product;
import com.tuanh.ecommerce.entity.user.User;
import com.tuanh.ecommerce.enums.ErrorCode;
import com.tuanh.ecommerce.exception.AppException;
import com.tuanh.ecommerce.mapper.CartMapper;
import com.tuanh.ecommerce.repository.CartProductRepository;
import com.tuanh.ecommerce.repository.CartRepository;
import com.tuanh.ecommerce.repository.ProductRepository;
import com.tuanh.ecommerce.repository.UserRepository;
import com.tuanh.ecommerce.util.SecurityUtils;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final CartMapper cartMapper;
    private final CartProductRepository cartProductRepository;
    private final ProductRepository productRepository;

    public void addToCart(AddToCartRequest request){
        User user = userRepository.findByUsername(SecurityUtils.getCurrentUsernameInSecurityContext()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXIST));
        Product product = productRepository.findById(request.getProductId()).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_EXIST));
        CartProduct cartProduct = CartProduct.builder()
                                        .cart(user.getCart())
                                        .product(product)
                                        .quantity(request.getQuantity())
                                        .build();
        cartProductRepository.save(cartProduct);
    }

    public CartResponse getItemsInCart(Long id) {
        Cart cart = cartRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.CART_NOT_EXIST));
        return cartMapper.fromCartToCartResponse(cart);
    }
}
