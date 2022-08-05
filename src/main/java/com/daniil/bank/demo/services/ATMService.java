package com.daniil.bank.demo.services;

import java.math.BigDecimal;

public interface ATMService {

    void depositOnCard(String cardNumber, String password, BigDecimal sum);

    void withdrawFromCard(String cardNumber, String password, BigDecimal sum);

}
