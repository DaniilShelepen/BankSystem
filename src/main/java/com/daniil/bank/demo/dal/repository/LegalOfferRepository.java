package com.daniil.bank.demo.dal.repository;

import com.daniil.bank.demo.dal.entity.legal.LegalOffer;
import com.daniil.bank.demo.enums.CURRENCY;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LegalOfferRepository extends JpaRepository<LegalOffer, Long> {
    @Transactional
    void deleteById(Long id);


    List<LegalOffer> findByCurrency(CURRENCY currency);
}
