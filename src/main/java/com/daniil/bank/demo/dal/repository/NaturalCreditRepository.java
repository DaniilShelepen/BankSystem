package com.daniil.bank.demo.dal.repository;

import com.daniil.bank.demo.dal.entity.natural.NaturalCredit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NaturalCreditRepository extends JpaRepository<NaturalCredit, Long> {
}
