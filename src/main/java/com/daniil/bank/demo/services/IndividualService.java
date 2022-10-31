package com.daniil.bank.demo.services;

import jdk.jfr.Description;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface IndividualService {

    /**
     * Перевод средств по iban-счёту
     **/
    @Description("")
    void payByIBAN(String iban, double sum, String cardNum, Long individualID);

    /**
     * Оплата в интернете
     **/
    @Description("")
    void onlinePay(String cardNumber, String acceptCardNumber, String CVV, LocalDate validity, double sum, Long individualID);

    /**
     * Перевод денег
     **/
    @Description("")
    void moneyTransfer(String cardNumber, String acceptCardNumber, double sum, Long individualID);

    /**
     * Оплата кредита
     **/
    void creditPay(String cardNumber, String creditNum, double sum, Long individualID);

}

