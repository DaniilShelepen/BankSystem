package com.daniil.bank.demo.mapper.impl;

import com.daniil.bank.demo.dal.entity.natural.NaturalOffer;
import com.daniil.bank.demo.dto.NaturalOfferDto;
import com.daniil.bank.demo.mapper.NaturalOfferConvertor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NaturalOfferConvertorImpl implements NaturalOfferConvertor {

    private final ModelMapper modelMapper;

    @Override
    public NaturalOfferDto convert(NaturalOffer naturalOffer) {
        return modelMapper.map(naturalOffer, NaturalOfferDto.class);
    }
}
