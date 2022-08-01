package com.daniil.bank.demo.mapper.impl;

import com.daniil.bank.demo.dal.entity.natural.Individual;
import com.daniil.bank.demo.dto.IndividualDto;
import com.daniil.bank.demo.mapper.IndividualConvertor;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
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
    public IndividualDto convert(Individual individual) {
        return mapper.map(individual, IndividualDto.class);
    }
}
