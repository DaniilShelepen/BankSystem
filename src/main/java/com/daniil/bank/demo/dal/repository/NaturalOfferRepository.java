package com.daniil.bank.demo.dal.repository;

import com.daniil.bank.demo.dal.entity.natural.NaturalOffer;
import com.daniil.bank.demo.finance.CURRENCY;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface NaturalOfferRepository extends JpaRepository<NaturalOffer, Integer> {
    @Transactional
    void deleteById(Integer id);


    List<NaturalOffer> findByCurrency(CURRENCY currency);
}
