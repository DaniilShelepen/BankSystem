package com.daniil.bank.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class EntityDto {

    private String name;
    private String typeOfOwn;
    private String address;
    private String phone;
}
