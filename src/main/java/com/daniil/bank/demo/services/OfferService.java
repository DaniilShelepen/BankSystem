package com.daniil.bank.demo.services;

import com.daniil.bank.demo.dto.LegalOfferDto;
import com.daniil.bank.demo.dto.NaturalOfferDto;

public interface OfferService {
    void createNaturalOffer(NaturalOfferDto naturalOfferDto);

    void createLegalOffer(LegalOfferDto legalOfferDto);

    void refactorNaturalOffer(Integer id, NaturalOfferDto naturalOfferDto);

    void refactorLegalOffer(Integer id, LegalOfferDto legalOfferDto);

    void deleteNaturalOffer(Integer id);

    void deleteLegalOffer(Integer id);
}
