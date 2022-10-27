package com.daniil.bank.demo.dto;

import com.daniil.bank.demo.enums.CLIENT_STATUS;
import com.daniil.bank.demo.enums.CREDIT_STATUS;
import com.daniil.bank.demo.enums.CURRENCY;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@SuperBuilder
public class LegalCreditDto {

    private CREDIT_STATUS status;
    private String number;
    private CLIENT_STATUS clientStatus;
    private BigDecimal sum;
    private LocalDate loanTerm;
    private Double percentageRate;
    private CURRENCY currency;
    private BigDecimal forfeit;


}
