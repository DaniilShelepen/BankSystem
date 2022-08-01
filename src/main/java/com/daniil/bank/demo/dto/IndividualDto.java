package com.daniil.bank.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class IndividualDto {
    //private Integer id;
    private String name;
    private String surname;
    private String thirdName;
    private String passportSeries;
    private String passportID;
    private String address;
    private String phoneNumber;
}
