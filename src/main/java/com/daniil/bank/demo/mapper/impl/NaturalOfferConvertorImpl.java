package com.daniil.bank.demo.mapper.impl;

import com.daniil.bank.demo.dal.entity.natural.NaturalOffer;
import com.daniil.bank.demo.dto.NaturalOfferDto;
import com.daniil.bank.demo.mapper.NaturalOfferConvertor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class NaturalOfferConvertorImpl implements NaturalOfferConvertor {

    private final ModelMapper mapper;

    public NaturalOfferConvertorImpl() {
        this.mapper = new ModelMapper();
    }

    @Override
    public NaturalOfferDto convert(NaturalOffer naturalOffer) {
        return mapper.map(naturalOffer, NaturalOfferDto.class);
    }
}
