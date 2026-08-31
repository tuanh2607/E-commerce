package com.tuanh.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tuanh.ecommerce.entity.cart_order.OrderProduct;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long>{
    
}
