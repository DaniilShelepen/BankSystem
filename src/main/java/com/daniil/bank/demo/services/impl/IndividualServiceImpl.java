package com.daniil.bank.demo.services.impl;

import com.daniil.bank.demo.dal.entity.BankAccount;
import com.daniil.bank.demo.dal.entity.natural.NaturalCredit;
import com.daniil.bank.demo.dal.repository.BankAccountRepository;
import com.daniil.bank.demo.dal.repository.BankCardRepository;
import com.daniil.bank.demo.dal.repository.NaturalCreditRepository;
import com.daniil.bank.demo.services.BankAccountService;
import com.daniil.bank.demo.services.IndividualService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IndividualServiceImpl implements IndividualService {

    private final BankAccountRepository bankAccountRepository;

    private final BankCardRepository bankCardRepository;

    private final BankAccountService bankAccountService;

    private final NaturalCreditRepository naturalCreditRepository;

    @Transactional
    @Override
    public void payByIBAN(String iban, double sum, String cardNum, Long individualID) {

        BankAccount acceptBankAccount = bankAccountRepository.findBankAccountByIBAN(iban);

        BankAccount givingBankAccount = Optional.ofNullable(bankCardRepository.findByCardNumber(cardNum).getBankAccount())
                .orElseThrow(RuntimeException::new);//todo

        verificationBankAccount(individualID, givingBankAccount);

        bankAccountService.transaction(sum, givingBankAccount, acceptBankAccount);

    }

    @Override
    public void onlinePay(String givingCardNumber, String acceptCardNumber, String CVV, LocalDate validity, double sum, Long individualID) {

        BankAccount acceptBankAccount = bankCardRepository.findByCardNumber(acceptCardNumber).getBankAccount();

        BankAccount givingBankAccount = Optional.ofNullable(bankCardRepository.findByCardNumber(givingCardNumber).getBankAccount())
                .orElseThrow(RuntimeException::new);//todo

        verificationBankAccount(individualID, givingBankAccount);

        bankAccountService.transaction(sum, givingBankAccount, acceptBankAccount);

    }


    @Override
    public void moneyTransfer(String givingCardNumber, String acceptCardNumber, double sum, Long individualID) {

    }

    @Transactional
    @Override
    public void creditPay(String cardNumber, String creditNum, double sum, Long individualID) {
        NaturalCredit credit = Optional.ofNullable(naturalCreditRepository.findByNumber(creditNum))
                .orElseThrow(RuntimeException::new);//todo

        BankAccount bankAccount = bankCardRepository.findByCardNumber(cardNumber).getBankAccount();

        verificationBankAccount(individualID, bankAccount);

        bankAccountService.naturalCredit(bankAccount, sum, credit);

    }


    private void verificationBankAccount(Long individualID, BankAccount bankAccount) {
        if (!bankAccount.getIndividualUser().getId().equals(individualID)) throw new RuntimeException();//todo
    }
}
