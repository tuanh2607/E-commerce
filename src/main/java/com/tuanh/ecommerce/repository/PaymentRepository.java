package com.tuanh.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tuanh.ecommerce.entity.payment.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long>{
    
}
