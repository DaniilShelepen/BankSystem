package com.daniil.bank.demo.mapper;

import com.daniil.bank.demo.dal.entity.legal.LegalOffer;
import com.daniil.bank.demo.dto.LegalOfferDto;

public interface LegalOfferConvertor {
    LegalOfferDto convertor(LegalOffer legalOffer);
}
