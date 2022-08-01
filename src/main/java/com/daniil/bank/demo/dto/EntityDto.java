package com.daniil.bank.demo.dto;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class EntityDto {

    private String name;
    private String typeOfOwn;
    private String address;
    private String phone;
}
