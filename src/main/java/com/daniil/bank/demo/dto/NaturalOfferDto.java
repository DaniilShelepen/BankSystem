package com.daniil.bank.demo.dto;

import com.daniil.bank.demo.enums.CLIENT_STATUS;
import com.daniil.bank.demo.enums.CURRENCY;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@SuperBuilder
public class NaturalOfferDto {

    private double sum;
    private String description;
    private Long months;
    private double percentageRate;// процентная ставка
    private CURRENCY currency;
    private CLIENT_STATUS client_status;
    private boolean available;

}
