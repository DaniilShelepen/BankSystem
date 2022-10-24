package com.daniil.bank.demo.mapper.impl;

import com.daniil.bank.demo.dal.entity.Operation;
import com.daniil.bank.demo.dto.OperationDto;
import com.daniil.bank.demo.mapper.OperationConvertor;
import org.springframework.stereotype.Component;

@Component
public class OperationConvertorImpl implements OperationConvertor {

    @Override
    public OperationDto convert(Operation operation) {


        if (operation.getEntityUser() == null) {
            return new OperationDto(operation.getIndividualUser().getName().toUpperCase() + " " +
                    operation.getIndividualUser().getSurname().toUpperCase() + " " +
                    operation.getIndividualUser().getThirdName().toUpperCase(), operation.getDescription(),
                    operation.getSum(), operation.getDate(), operation.getOperation_status());
        } else {
            return new OperationDto(operation.getEntityUser().getName().toUpperCase(), operation.getDescription(),
                    operation.getSum(), operation.getDate(), operation.getOperation_status());
        }


    }
}
