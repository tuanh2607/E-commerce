package com.tuanh.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tuanh.ecommerce.entity.cart_order.CartProduct;

public interface CartProductRepository extends JpaRepository<CartProduct, Long>{
    
}
