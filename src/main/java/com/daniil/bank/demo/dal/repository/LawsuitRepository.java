package com.daniil.bank.demo.dal.repository;

import com.daniil.bank.demo.dal.entity.Lawsuit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LawsuitRepository extends JpaRepository<Lawsuit, Long> {
}
