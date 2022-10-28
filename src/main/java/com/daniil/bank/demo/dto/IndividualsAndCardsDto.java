package com.daniil.bank.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IndividualsAndCardsDto {

    private String name;
    private String surname;
    private String cardName;
    private String cardNumber;
}
