package com.daniil.bank.demo.services;

import com.daniil.bank.demo.dal.entity.legal.EntityUser;
import com.daniil.bank.demo.dal.entity.legal.LegalCredit;
import com.daniil.bank.demo.dal.entity.natural.IndividualUser;
import com.daniil.bank.demo.dal.entity.natural.NaturalCredit;

import java.math.BigDecimal;

public interface PaymentService {

    /**
     * Создание отчета об оплате физ кредита
     **/
    void createIndividualPayment(IndividualUser individualUser, double sum, NaturalCredit naturalCredit);

    /**
     * Создание отчета об оплате юр кредита
     **/
    void createEntityPayment(EntityUser entityUser, double sum, LegalCredit legalCredit);

}
