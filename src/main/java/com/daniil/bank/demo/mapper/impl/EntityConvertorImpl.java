package com.daniil.bank.demo.mapper.impl;

import com.daniil.bank.demo.dal.entity.legal.Entity;
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
    public EntityDto convert(Entity entity) {
        return mapper.map(entity,EntityDto.class);
    }
}
