package com.daniil.bank.demo.services;

import com.daniil.bank.demo.dal.entity.natural.Guarantor;
import com.daniil.bank.demo.dto.*;
import com.daniil.bank.demo.enums.CURRENCY;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

public interface ManagerService {

    IndividualDto createIndividual(IndividualDto individualDto, CURRENCY currency);

    Guarantor createGuarantor(GuarantorDto guarantorDto);

    EntityDto createEntity(EntityDto entityDto, CURRENCY currency);

    List<NaturalOfferDto> getNaturalOffers(CURRENCY currency, BigDecimal sum);

    List<LegalOfferDto> getLegalOffers(CURRENCY currency, BigDecimal sum);

    @Transactional
    void createNaturalContract(IndividualDto individualDto, Long offerId, GuarantorDto guarantorDto, BigDecimal sum);

    @Transactional
    void createLegalContract(EntityDto entityDto, Long offerId, BigDecimal sum);




    //статусы выдавать
    //

}
