package com.daniil.bank.demo.mapper;

import com.daniil.bank.demo.dal.entity.legal.EntityUser;
import com.daniil.bank.demo.dto.EntityDto;

public interface EntityConvertor {

    EntityDto convert(EntityUser entityUser);

}
