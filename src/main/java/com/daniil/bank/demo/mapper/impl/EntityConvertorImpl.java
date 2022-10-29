package com.daniil.bank.demo.mapper.impl;

import com.daniil.bank.demo.dal.entity.legal.EntityUser;
import com.daniil.bank.demo.dto.EntityDto;
import com.daniil.bank.demo.mapper.EntityConvertor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class EntityConvertorImpl implements EntityConvertor {

    private final ModelMapper mapper;

    public EntityConvertorImpl() {
        this.mapper = new ModelMapper();
    }
    @Override
    public EntityDto convert(EntityUser entityUser) {
        return mapper.map(entityUser,EntityDto.class);
    }
}
