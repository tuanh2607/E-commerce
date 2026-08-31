package com.tuanh.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tuanh.ecommerce.entity.product.Image;

public interface ImageRepository extends JpaRepository<Image, Long>{
    
}
