package com.daniil.bank.demo.services;

import com.daniil.bank.demo.dal.entity.legal.EntityUser;
import com.daniil.bank.demo.dal.entity.legal.LegalOffer;
import com.daniil.bank.demo.dal.entity.Guarantor;
import com.daniil.bank.demo.dal.entity.natural.IndividualUser;
import com.daniil.bank.demo.dal.entity.natural.NaturalOffer;
import org.springframework.transaction.annotation.Transactional;

public interface ContractService {

    /**
     * Оформление кредита для физ лица
     **/
    @Transactional
    void individualContract(IndividualUser individualUser, NaturalOffer credit, Guarantor guarantor, double sum);

    /**
     * Оформление кредита для юр лица
     **/
    @Transactional
    void legalContract(EntityUser entityUser, LegalOffer credit, double sum);

}
