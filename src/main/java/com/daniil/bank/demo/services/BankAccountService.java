package com.daniil.bank.demo.services;

import com.daniil.bank.demo.dal.entity.BankAccount;
import com.daniil.bank.demo.dal.entity.legal.LegalCredit;
import com.daniil.bank.demo.dal.entity.natural.NaturalCredit;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

public interface BankAccountService {

    //отдать
    @Transactional
    void naturalCredit(BankAccount givingBankAccount, BigDecimal sum, NaturalCredit credit);

    @Transactional
    void legalCredit(BankAccount givingBankAccount, BigDecimal sum, LegalCredit credit);


    @Transactional
    void transaction(BigDecimal sum, BankAccount givingBankAccount, BankAccount acceptBankAccount);
}
