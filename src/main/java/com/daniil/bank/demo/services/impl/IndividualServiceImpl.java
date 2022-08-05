package com.daniil.bank.demo.services.impl;

import com.daniil.bank.demo.services.IndividualService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class IndividualServiceImpl implements IndividualService {

    @Override
    public void payByIBAN(String iban, BigDecimal sum) {

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
