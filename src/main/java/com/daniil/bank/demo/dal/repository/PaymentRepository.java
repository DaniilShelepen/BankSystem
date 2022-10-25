package com.daniil.bank.demo.dal.repository;

import com.daniil.bank.demo.dal.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
}
