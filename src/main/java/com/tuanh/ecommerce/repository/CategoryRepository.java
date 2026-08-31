package com.tuanh.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tuanh.ecommerce.entity.product.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
    
}
