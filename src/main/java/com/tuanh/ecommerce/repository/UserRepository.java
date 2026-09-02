package com.tuanh.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tuanh.ecommerce.entity.user.User;

public interface UserRepository extends JpaRepository<User, String>{
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
    boolean existsByUsername(String name);
    Optional<User> findByUsername(String username);
}
