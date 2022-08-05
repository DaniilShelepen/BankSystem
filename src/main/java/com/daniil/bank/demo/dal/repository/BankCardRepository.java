package com.daniil.bank.demo.dal.repository;

import com.daniil.bank.demo.dal.entity.BankCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankCardRepository extends JpaRepository<BankCard, Long> {
}
