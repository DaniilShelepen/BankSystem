package com.daniil.bank.demo.dto;

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
    private String estate;// имущество под залог
    private BigDecimal approximateCost; //примерная цена залога
}
