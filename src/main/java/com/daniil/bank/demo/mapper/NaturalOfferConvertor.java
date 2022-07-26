package com.daniil.bank.demo.mapper;

import com.daniil.bank.demo.dal.entity.natural.NaturalOffer;
import com.daniil.bank.demo.dto.NaturalOfferDto;

public interface NaturalOfferConvertor {
    NaturalOfferDto convert(NaturalOffer naturalOffer);
}
