package com.daniil.bank.demo.services;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface IndividualService {

    void payByIBAN(String iban, BigDecimal sum, String cardNum);

    void onlinePay(String cardNumber, String cardName, String CVV, LocalDate validity, BigDecimal sum);

    void moneyTransfer(String cardNumber, BigDecimal sum);


}

