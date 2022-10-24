package com.daniil.bank.demo.services;

import com.daniil.bank.demo.dal.entity.legal.EntityUser;
import com.daniil.bank.demo.dal.entity.legal.LegalOffer;
import com.daniil.bank.demo.dal.entity.natural.Guarantor;
import com.daniil.bank.demo.dal.entity.natural.IndividualUser;
import com.daniil.bank.demo.dal.entity.natural.NaturalOffer;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

public interface ContractService {
    @Transactional
    void individualContract(IndividualUser individualUser, NaturalOffer credit, Guarantor guarantor, BigDecimal sum);

    @Transactional
    void legalContract(EntityUser entityUser, LegalOffer credit, BigDecimal sum);

}
