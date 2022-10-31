package com.daniil.bank.demo.services;

import com.daniil.bank.demo.dal.entity.BankAccount;
import com.daniil.bank.demo.dal.entity.legal.EntityUser;
import com.daniil.bank.demo.dal.entity.legal.LegalCredit;
import com.daniil.bank.demo.dal.entity.natural.IndividualUser;
import com.daniil.bank.demo.dal.entity.natural.NaturalCredit;
import com.daniil.bank.demo.enums.CURRENCY;
import org.springframework.transaction.annotation.Transactional;

public interface BankAccountService {

    /**
     * Оплата по физическому кредиту
     **/
    @Transactional
    void naturalCreditPay(BankAccount givingBankAccount, double sum, NaturalCredit credit);

    /**
     * Оплата по юридическому кредиту
     **/
    @Transactional
    void legalCreditPay(BankAccount givingBankAccount, double sum, LegalCredit credit);

    /**
     * Создание физического банковского аккаунта
     **/
    void createNaturalBankAccount(IndividualUser individualUser, CURRENCY currency);

    /**
     * Создание юридического банковского аккаунта
     **/
    void createEntityBankAccount(EntityUser entityUser, CURRENCY currency);

    /**
     * Перевод средств
     **/
    @Transactional
    void transaction(double sum, BankAccount givingBankAccount, BankAccount acceptBankAccount);
}
