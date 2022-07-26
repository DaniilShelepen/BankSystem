package com.daniil.bank.demo.dal.repository;

import com.daniil.bank.demo.dal.entity.legal.LegalCredit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LegalCreditRepository extends JpaRepository<LegalCredit, Integer> {
}
