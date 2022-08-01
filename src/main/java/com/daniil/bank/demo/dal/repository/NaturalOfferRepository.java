package com.daniil.bank.demo.dal.repository;

import com.daniil.bank.demo.dal.entity.natural.NaturalOffer;
import com.daniil.bank.demo.enums.CURRENCY;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface NaturalOfferRepository extends JpaRepository<NaturalOffer, Long> {
    @Transactional
    void deleteById(Long id);


    List<NaturalOffer> findByCurrency(CURRENCY currency);
}
