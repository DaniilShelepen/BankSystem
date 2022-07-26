package com.daniil.bank.demo.dto;

import com.daniil.bank.demo.finance.CLIENT_STATUS;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@SuperBuilder
public class EntityDto {

    private String name;
    private String typeOfOwn;
    private String address;
    private String phone;
    private CLIENT_STATUS client_status;
}
