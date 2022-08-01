package com.daniil.bank.demo.services;

import com.daniil.bank.demo.dto.LegalOfferDto;
import com.daniil.bank.demo.dto.NaturalOfferDto;

public interface OfferService {
    void createNaturalOffer(NaturalOfferDto naturalOfferDto);

    void createLegalOffer(LegalOfferDto legalOfferDto);

    void refactorNaturalOffer(Long id, NaturalOfferDto naturalOfferDto);

    void refactorLegalOffer(Long id, LegalOfferDto legalOfferDto);

    void deleteNaturalOffer(Long id);

    void deleteLegalOffer(Long id);
}
