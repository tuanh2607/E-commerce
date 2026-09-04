package com.tuanh.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tuanh.ecommerce.entity.user.InvalidatedToken;

public interface InvalidatedTokenRepository extends JpaRepository<InvalidatedToken, String>{
    boolean existsById(String id);
}
