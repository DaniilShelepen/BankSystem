package com.daniil.bank.demo.services.impl;

import com.daniil.bank.demo.dal.entity.legal.Entity;
import com.daniil.bank.demo.dal.entity.natural.Guarantor;
import com.daniil.bank.demo.dal.entity.natural.Individual;
import com.daniil.bank.demo.dal.entity.natural.NaturalCredit;
import com.daniil.bank.demo.dal.entity.natural.NaturalOffer;
import com.daniil.bank.demo.dal.repository.*;
import com.daniil.bank.demo.dto.*;
import com.daniil.bank.demo.finance.CLIENT_STATUS;
import com.daniil.bank.demo.finance.CREDIT_STATUS;
import com.daniil.bank.demo.finance.CURRENCY;
import com.daniil.bank.demo.mapper.NaturalOfferConvertor;
import com.daniil.bank.demo.services.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ManagerServiceImpl implements ManagerService {

    private final IndividualRepository individualRepository;
    private final EntityRepository entityRepository;
    private final GuarantorRepository guarantorRepository;
    private final NaturalCreditRepository naturalCreditRepository;
    private final LegalCreditRepository legalCreditRepository;
    private final NaturalOfferRepository naturalOfferRepository;
    private final LegalOfferRepository legalOfferRepository;

    private final NaturalOfferConvertor naturalOfferConvertor;

    @Override
    public Individual createIndividual(IndividualDto individualDto) {

        Individual individualDB = individualRepository.findByPassportIDAndPassportSeries(individualDto.getPassportID(), individualDto.getPassportSeries());

        return Objects.requireNonNullElseGet(individualDB, () -> individualRepository.save(Individual.builder()
                .name(individualDto.getName())
                .surname(individualDto.getSurname())
                .thirdName(individualDto.getThirdName())
                .passportSeries(individualDto.getPassportSeries())
                .passportID(individualDto.getPassportID())
                .address(individualDto.getAddress())
                .phoneNumber(individualDto.getPhoneNumber())
                .client_status(CLIENT_STATUS.GENERAL)
                .build()));
    }

    @Override
    public Guarantor createGuarantor(GuarantorDto guarantorDto) {

        Guarantor guarantorDB = guarantorRepository.findByPassportIDAndPassportSeries(guarantorDto.getPassportID(), guarantorDto.getPassportSeries());

        return Objects.requireNonNullElseGet(guarantorDB, () -> guarantorRepository.save(Guarantor.builder()
                .name(guarantorDto.getName())
                .surname(guarantorDto.getSurname())
                .thirdName(guarantorDto.getThirdName())
                .passportSeries(guarantorDto.getPassportSeries())
                .passportID(guarantorDto.getPassportID())
                .address(guarantorDto.getAddress())
                .phoneNumber(guarantorDto.getPhoneNumber())
                .estate(guarantorDto.getEstate())
                .approximateCost(guarantorDto.getApproximateCost())
                .available(true)
                .build()));
    }

    @Override
    public Entity createEntity(EntityDto entityDto) {
        return null;
    }

    @Override
    public List<NaturalOfferDto> getNaturalOffers(CURRENCY currency) {
        naturalOfferRepository.findByCurrency(currency).stream()
                .map(naturalOfferConvertor::convert).collect(Collectors.toList());
        return null;
    }

    @Override
    public List<LegalOfferDto> getLegalOffers(CURRENCY currency) {
        return null;
    }

    @Override
    public void executionNaturalContract(IndividualDto individualDto, Integer offerId, GuarantorDto guarantorDto, BigDecimal amount) {
        Individual client = createIndividual(individualDto);
        NaturalOffer credit = naturalOfferRepository.findById(offerId).orElseThrow(RuntimeException::new);//todo своя ошибка
        if (client.getClient_status().compareTo(credit.getClient_status()) < 0)
            throw new RuntimeException();//todo exeption
        Guarantor guarantor = createGuarantor(guarantorDto);

        switch (client.getClient_status()) {

            case BENEFIT -> {
                naturalCreditRepository.save(NaturalCredit.builder()
                        .amount(amount)
                        .loanTerm(LocalDate.now().plusMonths(credit.getTimeMonth()))//todo тут посмотри как поставить последний день получившегося месяца
                        .status(CREDIT_STATUS.PROCESSING)
                        .client_status(client.getClient_status())
                        .percentageRate((int) Math.round(credit.getPercentageRate() - (credit.getPercentageRate() * 0.1)))
                        .currency(credit.getCurrency())
                        .forfeit(BigDecimal.ZERO)
                        .guarantor(null)
                        .individual(client)
                        .build());

            }

            case REGULAR -> {
                if (!guarantor.isAvailable())
                    throw new RuntimeException();//todo exeption, подумай как еще от ифов избавиться

                naturalCreditRepository.save(NaturalCredit.builder()
                        .amount(amount)
                        .loanTerm(LocalDate.now().plusMonths(credit.getTimeMonth()))//todo тут посмотри как поставиьт последний день получившегося месяца
                        .status(CREDIT_STATUS.PROCESSING)
                        .client_status(client.getClient_status())
                        .percentageRate((int) Math.round(credit.getPercentageRate() - (credit.getPercentageRate() * 0.3)))
                        .currency(credit.getCurrency())
                        .forfeit(BigDecimal.ZERO)
                        .guarantor(guarantor)
                        .individual(client)
                        .build());

                guarantor.setAvailable(false);
                guarantorRepository.save(guarantor);//todo попробуй эту строчку потом убери, типо оно и так обновится должно
            }

            case VIP -> {
                naturalCreditRepository.save(NaturalCredit.builder()
                        .amount(amount)
                        .loanTerm(LocalDate.now().plusMonths(credit.getTimeMonth()))//todo тут посмотри как поставить последний день получившегося месяца
                        .status(CREDIT_STATUS.PROCESSING)
                        .client_status(client.getClient_status())
                        .percentageRate((int) Math.round(credit.getPercentageRate() - (credit.getPercentageRate() * 0.4)))
                        .currency(credit.getCurrency())
                        .forfeit(BigDecimal.ZERO)
                        .guarantor(null)
                        .individual(client)
                        .build());
            }

            default -> {
                if (!guarantor.isAvailable())
                    throw new RuntimeException();//todo exeption, подумай как еще от ифов избавиться

                naturalCreditRepository.save(NaturalCredit.builder()
                        .amount(amount)
                        .loanTerm(LocalDate.now().plusMonths(credit.getTimeMonth()))//todo тут посмотри как поставиьт последний день получившегося месяца
                        .status(CREDIT_STATUS.PROCESSING)
                        .client_status(client.getClient_status())
                        .percentageRate(credit.getPercentageRate())
                        .currency(credit.getCurrency())
                        .forfeit(BigDecimal.ZERO)
                        .guarantor(guarantor)
                        .individual(client)
                        .build());

                guarantor.setAvailable(false);
                guarantorRepository.save(guarantor);//todo попробуй эту строчку потом убери, типо оно и так обновится должно
            }

        }
    }


    @Override
    public void executionLegalContract(EntityDto entityDto, Integer offerId) {

    }

    @Override
    public IndividualDto refactorIndividualData(Integer id, IndividualDto individualDto) {
        return null;
    }

    @Override
    public EntityDto refactorEntityData(Integer id, EntityDto entityDto) {
        return null;
    }
}
