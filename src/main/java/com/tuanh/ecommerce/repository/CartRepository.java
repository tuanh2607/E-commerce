package com.tuanh.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tuanh.ecommerce.entity.cart_order.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>{
    
}
