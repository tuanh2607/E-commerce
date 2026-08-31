package com.tuanh.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tuanh.ecommerce.entity.cart_order.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{
    
}
