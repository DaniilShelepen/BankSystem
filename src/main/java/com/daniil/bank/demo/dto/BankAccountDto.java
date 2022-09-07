package com.daniil.bank.demo.dto;

import com.daniil.bank.demo.enums.ACCOUNT_STATUS;
import com.daniil.bank.demo.enums.CURRENCY;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@SuperBuilder
public class BankAccountDto {

    private String IBAN;
    private BigDecimal balance;
    private CURRENCY currency;
    private ACCOUNT_STATUS accountStatus;
}
