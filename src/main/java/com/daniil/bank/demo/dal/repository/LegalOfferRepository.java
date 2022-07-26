package com.daniil.bank.demo.dal.repository;

import com.daniil.bank.demo.dal.entity.legal.LegalOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface LegalOfferRepository extends JpaRepository<LegalOffer, Integer> {
    @Transactional
    void deleteById(Integer id);
}
