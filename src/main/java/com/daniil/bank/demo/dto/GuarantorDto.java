package com.daniil.bank.demo.dto;

import com.daniil.bank.demo.enums.CURRENCY;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@SuperBuilder
public class GuarantorDto {
    private String name;
    private String surname;
    private String thirdName;
    private String passportSeries;
    private String passportID;
    private String address;
    private String phoneNumber;
    private CURRENCY currency;
    private String estate;// имущество под залог
    private double approximateCost; //примерная цена залога
}
