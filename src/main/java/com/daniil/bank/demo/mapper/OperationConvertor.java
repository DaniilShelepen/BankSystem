package com.daniil.bank.demo.mapper;

import com.daniil.bank.demo.dal.entity.Operation;
import com.daniil.bank.demo.dto.OperationDto;

public interface OperationConvertor {

    OperationDto convert(Operation operation);

}
