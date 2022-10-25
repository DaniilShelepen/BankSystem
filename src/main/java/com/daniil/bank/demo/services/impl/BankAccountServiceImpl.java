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
    public void naturalCredit(BankAccount givingBankAccount, BigDecimal sum, NaturalCredit credit) {
        BalanceCheck(givingBankAccount, sum);

        givingBankAccount.setBalance(givingBankAccount.getBalance().subtract(sum));
        bankAccountRepository.save(givingBankAccount);

        credit.setSum(credit.getSum().subtract(sum));
        naturalCreditRepository.save(credit);

        paymentService.createIndividualPayment(givingBankAccount.getIndividualUser(), sum, credit);

    }

    @Override
    @Transactional
    public void legalCredit(BankAccount givingBankAccount, BigDecimal sum, LegalCredit credit) {

    }

    @Override
    @Transactional
    public void transaction(BigDecimal sum, BankAccount givingBankAccount, BankAccount acceptBankAccount) {
        BalanceCheck(givingBankAccount, sum);

        givingBankAccount.setBalance(givingBankAccount.getBalance().subtract(sum));
        bankAccountRepository.save(givingBankAccount);

        if (acceptBankAccount != null) {
            acceptBankAccount.setBalance(acceptBankAccount.getBalance().add(sum));
            bankAccountRepository.save(acceptBankAccount);
        }
    }

    private static void BalanceCheck(BankAccount givingBankAccount, BigDecimal sum) {
        if (givingBankAccount.getBalance().compareTo(sum) < 0)
            throw new RuntimeException();//todo
    }


}
