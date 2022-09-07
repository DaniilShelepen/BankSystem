package com.daniil.bank.demo.services;

import com.daniil.bank.demo.dal.entity.legal.Entity;
import com.daniil.bank.demo.dal.entity.legal.LegalOffer;
import com.daniil.bank.demo.dal.entity.natural.Guarantor;
import com.daniil.bank.demo.dal.entity.natural.Individual;
import com.daniil.bank.demo.dal.entity.natural.NaturalOffer;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

public interface ContractService {
    @Transactional
    void individualContract(Individual individual, NaturalOffer credit, Guarantor guarantor, BigDecimal sum);

    @Transactional
    void legalContract(Entity entity, LegalOffer credit, BigDecimal sum);

}
