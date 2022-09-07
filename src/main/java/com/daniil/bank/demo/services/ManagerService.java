package com.daniil.bank.demo.services;

import com.daniil.bank.demo.dal.entity.natural.Guarantor;
import com.daniil.bank.demo.dto.*;
import com.daniil.bank.demo.enums.CARD_TYPE;
import com.daniil.bank.demo.enums.CURRENCY;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

public interface ManagerService {
    @Transactional
    IndividualDto createIndividual(IndividualDto individualDto, CURRENCY currency);

    Guarantor createGuarantor(GuarantorDto guarantorDto);

    @Transactional
    EntityDto createEntity(EntityDto entityDto, CURRENCY currency);

    List<NaturalOfferDto> getNaturalOffers(CURRENCY currency, BigDecimal sum);

    List<LegalOfferDto> getLegalOffers(CURRENCY currency, BigDecimal sum);

    void createNaturalContract(IndividualDto individualDto, Long offerId, GuarantorDto guarantorDto, BigDecimal sum);

    void createLegalContract(EntityDto entityDto, Long offerId, BigDecimal sum);

    @Transactional
    void createIndividualCard(IndividualDto individualDto, CARD_TYPE cardType, Integer bankAccNum);

    @Transactional
    void createEntityCard(EntityDto entityDto, IndividualDto individualDto, CARD_TYPE cardType, Integer bankAccNum);

    List<BankAccountDto> getIndividualBankAccList(String passportID, String passportSeries);

    List<BankAccountDto> getEntityBankAccList(String name);

    String getIndividualINFO(String passportID, String passportSeries);

    String getEntityINFO(String name);

}
