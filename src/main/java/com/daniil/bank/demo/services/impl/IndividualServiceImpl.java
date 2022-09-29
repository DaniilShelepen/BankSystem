package com.daniil.bank.demo.services.impl;

import com.daniil.bank.demo.dal.entity.BankAccount;
import com.daniil.bank.demo.dal.repository.BankAccountRepository;
import com.daniil.bank.demo.dal.repository.BankCardRepository;
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

    @Transactional
    @Override
    public void payByIBAN(String iban, BigDecimal sum, String cardNum, Long individualID) {

        BankAccount acceptBankAccount = bankAccountRepository.findBankAccountByIBAN(iban);

        BankAccount givingBankAccount = Optional.ofNullable(bankCardRepository.findByCardNumber(cardNum).getBankAccount())
                .orElseThrow(RuntimeException::new);//todo
        if (yourCard(individualID, givingBankAccount)) throw new RuntimeException();

        balanceOperation(sum, givingBankAccount, acceptBankAccount);

    }

    @Override
    public void onlinePay(String givingCardNumber, String acceptCardNumber, String CVV, LocalDate validity, BigDecimal sum, Long individualID) {

        BankAccount givingBankAccount = Optional.ofNullable(bankCardRepository.findByCardNumber(givingCardNumber).getBankAccount())
                .orElseThrow(RuntimeException::new);//todo

        if (yourCard(individualID, givingBankAccount)) throw new RuntimeException();


        BankAccount acceptBankAccount = bankCardRepository.findByCardNumber(acceptCardNumber).getBankAccount();

        balanceOperation(sum, givingBankAccount, acceptBankAccount);

    }

    private void balanceOperation(BigDecimal sum, BankAccount givingBankAccount, BankAccount acceptBankAccount) {
        if (givingBankAccount.getBalance().compareTo(sum) < 0)
            throw new RuntimeException();//todo

        givingBankAccount.setBalance(givingBankAccount.getBalance().subtract(sum));
        bankAccountRepository.save(givingBankAccount);

        if (acceptBankAccount != null) {
            acceptBankAccount.setBalance(acceptBankAccount.getBalance().add(sum));
            bankAccountRepository.save(acceptBankAccount);
        }
    }

    @Override
    public void moneyTransfer(String givingCardNumber, String acceptCardNumber, BigDecimal sum, Long individualID) {

    }


    private boolean yourCard(Long individualID, BankAccount bankAccount) {
        return !bankAccount.getIndividual().getId().equals(individualID);
    }
}
