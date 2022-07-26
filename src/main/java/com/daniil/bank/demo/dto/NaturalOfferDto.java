package com.daniil.bank.demo.dto;

import com.daniil.bank.demo.finance.CLIENT_STATUS;
import com.daniil.bank.demo.finance.CURRENCY;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@SuperBuilder
public class NaturalOfferDto {
    //  private Integer id;
    private BigDecimal credit;
    private String description;
    private String time;
    private Integer percentageRate;// процентная ставка
    private CURRENCY currency;
    private CLIENT_STATUS client_status;
    private boolean available;

}
