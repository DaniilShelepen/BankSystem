package com.daniil.bank.demo.services;

import jdk.jfr.Description;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface IndividualService {

    @Description("")
    void payByIBAN(String iban, BigDecimal sum, String cardNum, Long individualID);

    @Description("")
    void onlinePay(String cardNumber, String acceptCardNumber, String CVV, LocalDate validity, BigDecimal sum, Long individualID);

    @Description("")
    void moneyTransfer(String cardNumber, String acceptCardNumber, BigDecimal sum, Long individualID);


}

