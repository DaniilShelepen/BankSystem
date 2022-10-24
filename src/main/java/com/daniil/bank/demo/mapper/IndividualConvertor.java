package com.daniil.bank.demo.mapper;

import com.daniil.bank.demo.dal.entity.natural.IndividualUser;
import com.daniil.bank.demo.dto.IndividualDto;

public interface IndividualConvertor {
    IndividualDto convert(IndividualUser individualUser);
}
