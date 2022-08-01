package com.daniil.bank.demo.mapper.impl;

import com.daniil.bank.demo.dal.entity.legal.LegalOffer;
import com.daniil.bank.demo.dto.LegalOfferDto;
import com.daniil.bank.demo.mapper.LegalOfferConvertor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class LegalOfferConvertorImpl implements LegalOfferConvertor {

    private final ModelMapper mapper;

    public LegalOfferConvertorImpl() {
        this.mapper = new ModelMapper();
    }

    @Override
    public LegalOfferDto convertor(LegalOffer legalOffer) {
        return mapper.map(legalOffer, LegalOfferDto.class);
    }
}
