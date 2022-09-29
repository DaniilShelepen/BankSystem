package com.daniil.bank.demo.dto;

import com.daniil.bank.demo.enums.OPERATION_STATUS;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.Date;

@Data
@SuperBuilder
@AllArgsConstructor
public class OperationDto {

    private String fullName;
    private String description;
    private BigDecimal sum;
    private Date date;
    private OPERATION_STATUS operation_status;

}
