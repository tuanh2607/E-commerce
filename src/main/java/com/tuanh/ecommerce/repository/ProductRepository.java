package com.tuanh.ecommerce.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tuanh.ecommerce.entity.product.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
    @Query("SELECT p FROM Product AS p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Product> findByNameAndPagination(@Param("keyword") String keyword, Pageable pageable);
}
