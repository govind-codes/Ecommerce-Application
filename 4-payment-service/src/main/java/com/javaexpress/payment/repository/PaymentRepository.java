package com.javaexpress.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaexpress.payment.models.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long>{

}
