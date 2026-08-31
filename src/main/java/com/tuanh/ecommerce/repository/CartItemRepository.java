package com.tuanh.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tuanh.ecommerce.entity.cart_order.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long>{
    
}
