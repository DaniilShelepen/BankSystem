package com.daniil.bank.demo.services;

import com.daniil.bank.demo.dal.entity.Guarantor;
import com.daniil.bank.demo.dto.*;
import com.daniil.bank.demo.enums.CARD_TYPE;
import com.daniil.bank.demo.enums.CURRENCY;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ManagerService {

    /**
     * Создание физ лица
     **/
    @Transactional
    IndividualDto createIndividual(IndividualDto individualDto, CURRENCY currency);

    /**
     * Создание поручителя
     **/
    Guarantor createGuarantor(GuarantorDto guarantorDto);

    /**
     * Создание юр лица
     **/
    @Transactional
    EntityDto createEntity(EntityDto entityDto, CURRENCY currency);

    /**
     * Просмотр физ предложений по кредитам
     **/
    List<NaturalOfferDto> getNaturalOffers(CURRENCY currency, double sum);

    /**
     * Просмотр юр предложений по кредитам
     **/
    List<LegalOfferDto> getLegalOffers(CURRENCY currency, double sum);

    /**
     * Оформление договора на физ кредит
     **/
    void createNaturalContract(IndividualDto individualDto, Long offerId, GuarantorDto guarantorDto, double sum);

    /**
     * Оформление договора на юр кредит
     **/
    void createLegalContract(EntityDto entityDto, Long offerId, double sum);

    /**
     * Оформление договора на физ карту
     **/
    @Transactional
    void createIndividualCard(IndividualDto individualDto, CARD_TYPE cardType, Integer bankAccNum);

    /**
     * Оформление договора на юр карту
     **/
    @Transactional
    void createEntityCard(EntityDto entityDto, IndividualDto individualDto, CARD_TYPE cardType, Integer bankAccNum);

    /**
     * Просмотр банковских аккаунтов физ лица
     **/
    List<BankAccountDto> getIndividualBankAccList(String passportID, String passportSeries);

    /**
     * Просмотр банковских аккаунтов юр лица
     **/
    List<BankAccountDto> getEntityBankAccList(String name);

    /**
     * Просмотр информации о физ лице
     **/
    String getIndividualINFO(String passportID, String passportSeries);

    /**
     * Просмотр информации о юр лице
     **/
    String getEntityINFO(String name);

}
