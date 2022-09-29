package com.daniil.bank.demo.mapper.impl;

import com.daniil.bank.demo.dal.entity.Operation;
import com.daniil.bank.demo.dto.OperationDto;
import com.daniil.bank.demo.mapper.OperationConvertor;
import org.springframework.stereotype.Component;

@Component
public class OperationConvertorImpl implements OperationConvertor {

    @Override
    public OperationDto convert(Operation operation) {


        if (operation.getEntity() == null) {
            return new OperationDto(operation.getIndividual().getName().toUpperCase() + " " +
                    operation.getIndividual().getSurname().toUpperCase() + " " +
                    operation.getIndividual().getThirdName().toUpperCase(), operation.getDescription(),
                    operation.getSum(), operation.getDate(), operation.getOperation_status());
        } else {
            return new OperationDto(operation.getEntity().getName().toUpperCase(), operation.getDescription(),
                    operation.getSum(), operation.getDate(), operation.getOperation_status());
        }


    }
}
