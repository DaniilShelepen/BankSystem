package com.daniil.bank.demo.dto;

import com.daniil.bank.demo.enums.CARD_TYPE;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@SuperBuilder
public class BankCardDto {

    private String cardName;
    private String cardNumber;
    private LocalDate validity;
    private String CVV;
    private String password;
    CARD_TYPE cardType;
    private boolean corporate;

}
