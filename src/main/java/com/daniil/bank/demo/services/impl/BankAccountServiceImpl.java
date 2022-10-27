package com.daniil.bank.demo.services.impl;

import com.daniil.bank.demo.dal.entity.BankAccount;
import com.daniil.bank.demo.dal.entity.legal.LegalCredit;
import com.daniil.bank.demo.dal.entity.natural.NaturalCredit;
import com.daniil.bank.demo.dal.repository.BankAccountRepository;
import com.daniil.bank.demo.dal.repository.NaturalCreditRepository;
import com.daniil.bank.demo.services.BankAccountService;
import com.daniil.bank.demo.services.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class BankAccountServiceImpl implements BankAccountService {


    private final BankAccountRepository bankAccountRepository;

    private final PaymentService paymentService;

    private final NaturalCreditRepository naturalCreditRepository;


    @Override
    @Transactional
    public void naturalCredit(BankAccount givingBankAccount, double sum, NaturalCredit credit) {
        BalanceCheck(givingBankAccount, sum);

        givingBankAccount.setBalance(givingBankAccount.getBalance() - sum);
        bankAccountRepository.save(givingBankAccount);

        credit.setSum(credit.getSum() - sum);
        naturalCreditRepository.save(credit);

        paymentService.createIndividualPayment(givingBankAccount.getIndividualUser(), sum, credit);

    }

    @Override
    @Transactional
    public void legalCredit(BankAccount givingBankAccount, double sum, LegalCredit credit) {

    }

    @Override
    @Transactional
    public void transaction(double sum, BankAccount givingBankAccount, BankAccount acceptBankAccount) {
        BalanceCheck(givingBankAccount, sum);

        givingBankAccount.setBalance(givingBankAccount.getBalance() - sum);
        bankAccountRepository.save(givingBankAccount);

        if (acceptBankAccount != null) {
            acceptBankAccount.setBalance(acceptBankAccount.getBalance() + sum);
            bankAccountRepository.save(acceptBankAccount);
        }
    }

    private static void BalanceCheck(BankAccount givingBankAccount, double sum) {
        if (givingBankAccount.getBalance() < sum)
            throw new RuntimeException();//todo
    }


}
