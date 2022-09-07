package com.daniil.bank.demo.mapper.impl;

import com.daniil.bank.demo.dal.entity.BankAccount;
import com.daniil.bank.demo.dto.BankAccountDto;
import com.daniil.bank.demo.mapper.BankAccountConvertor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BankAccountConvertorImpl implements BankAccountConvertor {

    private final ModelMapper mapper;

    public BankAccountConvertorImpl() {
        this.mapper = new ModelMapper();
    }


    @Override
    public BankAccountDto convert(BankAccount account) {
        return mapper.map(account, BankAccountDto.class);
    }
}
