package com.daniil.bank.demo.services;

import jdk.jfr.Description;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface IndividualService {

    @Description("")
    void payByIBAN(String iban, BigDecimal sum, String cardNum);

    @Description("")
    void onlinePay(String cardNumber, String cardName, String CVV, LocalDate validity, BigDecimal sum);

    @Description("")
    void moneyTransfer(String cardNumber, BigDecimal sum);


}

