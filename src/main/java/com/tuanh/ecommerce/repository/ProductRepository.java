package com.tuanh.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tuanh.ecommerce.entity.product.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
    
}
