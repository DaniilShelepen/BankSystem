package com.daniil.bank.demo.mapper.impl;

import com.daniil.bank.demo.dal.entity.natural.IndividualUser;
import com.daniil.bank.demo.dto.IndividualDto;
import com.daniil.bank.demo.mapper.IndividualConvertor;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class IndividualConvertorImpl implements IndividualConvertor {

    private final ModelMapper mapper;


    public IndividualConvertorImpl() {
        this.mapper = new ModelMapper();
    }

    @Override
    public IndividualDto convert(IndividualUser individualUser) {
        return mapper.map(individualUser, IndividualDto.class);
    }
}
