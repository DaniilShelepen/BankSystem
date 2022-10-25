package com.daniil.bank.demo.services;

import com.daniil.bank.demo.dal.entity.legal.EntityUser;
import com.daniil.bank.demo.dal.entity.legal.LegalCredit;
import com.daniil.bank.demo.dal.entity.natural.IndividualUser;
import com.daniil.bank.demo.dal.entity.natural.NaturalCredit;

import java.math.BigDecimal;

public interface PaymentService {


    void createIndividualPayment(IndividualUser individualUser, BigDecimal sum, NaturalCredit naturalCredit);

    void createEntityPayment(EntityUser entityUser, BigDecimal sum, LegalCredit legalCredit);

}