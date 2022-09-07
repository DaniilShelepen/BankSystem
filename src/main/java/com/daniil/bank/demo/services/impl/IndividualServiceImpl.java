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
    public void payByIBAN(String iban, BigDecimal sum, String cardNum) {

        BankAccount acceptBankAccount = bankAccountRepository.findBankAccountByIBAN(iban);

        BankAccount givingBankAccount = Optional.ofNullable(bankCardRepository.findByCardNumber(cardNum).getBankAccount())
                .orElseThrow(RuntimeException::new);//todo

        if (givingBankAccount.getBalance().compareTo(sum) < 0)
            throw new RuntimeException();//todo
        if (acceptBankAccount != null) {

            givingBankAccount.setBalance(givingBankAccount.getBalance().subtract(sum));
            bankAccountRepository.save(givingBankAccount);

            acceptBankAccount.setBalance(acceptBankAccount.getBalance().add(sum));
            bankAccountRepository.save(acceptBankAccount);
        }


//ищем в моем банке
        //если есть - кидаем на счёт ибана и списываем
        //если нет - просто так списываем
    }

    @Override
    public void onlinePay(String cardNumber, String cardName, String CVV, LocalDate validity, BigDecimal sum) {

    }

    @Override
    public void moneyTransfer(String cardNumber, BigDecimal sum) {

    }


    private static void access() {

    }

}
