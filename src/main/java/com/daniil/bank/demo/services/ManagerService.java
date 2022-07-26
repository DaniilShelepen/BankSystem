package com.daniil.bank.demo.services;

import com.daniil.bank.demo.dal.entity.legal.Entity;
import com.daniil.bank.demo.dal.entity.natural.Guarantor;
import com.daniil.bank.demo.dal.entity.natural.Individual;
import com.daniil.bank.demo.dto.*;
import com.daniil.bank.demo.finance.CURRENCY;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

public interface ManagerService {

    Individual createIndividual(IndividualDto individualDto);

    Guarantor createGuarantor(GuarantorDto guarantorDto);

    Entity createEntity(EntityDto entityDto);

    List<NaturalOfferDto> getNaturalOffers(CURRENCY currency);

    List<LegalOfferDto> getLegalOffers(CURRENCY currency);

    @Transactional
    void executionNaturalContract(IndividualDto individualDto, Integer offerId, GuarantorDto guarantorDto, BigDecimal amount);

    @Transactional
    void executionLegalContract(EntityDto entityDto, Integer offerId);


    IndividualDto refactorIndividualData(Integer id, IndividualDto individualDto);

    EntityDto refactorEntityData(Integer id, EntityDto entityDto);
    //изменение двнных клиента
    //статусы выдавать
    //

}
