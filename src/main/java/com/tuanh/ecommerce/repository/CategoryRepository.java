package com.tuanh.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tuanh.ecommerce.entity.product.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
    @Query("SELECT c FROM Category AS c LEFT JOIN FETCH c.childrens WHERE c.id = :parentId")
    Optional<Category> findByIdWithChildren(@Param("parentId") Long parentId);
}
