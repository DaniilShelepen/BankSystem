package com.daniil.bank.demo.services;

import com.daniil.bank.demo.dto.LegalOfferDto;
import com.daniil.bank.demo.dto.NaturalOfferDto;

public interface OfferService {

    /**
     * Создание предложения по физ кредиту
     **/
    void createNaturalOffer(NaturalOfferDto naturalOfferDto);

    /**
     * Создание предложения по юр кредиту
     **/
    void createLegalOffer(LegalOfferDto legalOfferDto);

    /**
     * Редактирование предложения по физ кредиту
     **/
    void refactorNaturalOffer(Long id, NaturalOfferDto naturalOfferDto);

    /**
     * Редактирование предложения по юр кредиту
     **/
    void refactorLegalOffer(Long id, LegalOfferDto legalOfferDto);

    /**
     * Удалить предложения по физ кредиту
     **/
    void deleteNaturalOffer(Long id);

    /**
     * Удалить предложения по юр кредиту
     **/
    void deleteLegalOffer(Long id);
}
