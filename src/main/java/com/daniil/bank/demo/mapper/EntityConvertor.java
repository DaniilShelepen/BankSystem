package com.daniil.bank.demo.mapper;

import com.daniil.bank.demo.dal.entity.legal.Entity;
import com.daniil.bank.demo.dto.EntityDto;

public interface EntityConvertor {

    EntityDto convert(Entity entity);

}
