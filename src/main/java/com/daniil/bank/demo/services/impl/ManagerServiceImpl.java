package com.daniil.bank.demo.services.impl;

import com.daniil.bank.demo.dal.entity.BankAccount;
import com.daniil.bank.demo.dal.entity.BankCard;
import com.daniil.bank.demo.dal.entity.legal.Entity;
import com.daniil.bank.demo.dal.entity.legal.LegalCredit;
import com.daniil.bank.demo.dal.entity.legal.LegalOffer;
import com.daniil.bank.demo.dal.entity.natural.Guarantor;
import com.daniil.bank.demo.dal.entity.natural.Individual;
import com.daniil.bank.demo.dal.entity.natural.NaturalCredit;
import com.daniil.bank.demo.dal.entity.natural.NaturalOffer;
import com.daniil.bank.demo.dal.repository.*;
import com.daniil.bank.demo.dto.*;
import com.daniil.bank.demo.enums.*;
import com.daniil.bank.demo.mapper.EntityConvertor;
import com.daniil.bank.demo.mapper.IndividualConvertor;
import com.daniil.bank.demo.mapper.LegalOfferConvertor;
import com.daniil.bank.demo.mapper.NaturalOfferConvertor;
import com.daniil.bank.demo.services.ManagerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

@Slf4j
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
    private final IndividualConvertor individualConvertor;
    private final NaturalOfferConvertor naturalOfferConvertor;
    private final EntityConvertor entityConvertor;
    private final LegalOfferConvertor legalOfferConvertor;
    private final BankAccountRepository bankAccountRepository;

    private final BankCardRepository bankCardRepository;

    private String getIban() {
        Iban iban;

        do {
            iban = new Iban.Builder()
                    .countryCode(CountryCode.BY)
                    .bankCode("1707")
                    .buildRandom();
        }
        while (bankAccountRepository.findBankAccountByIBAN(String.valueOf(iban)) != null);
        return iban.toString();
    }

    @Override
    public IndividualDto createIndividual(IndividualDto individualDto, CURRENCY currency) {
        Individual individualDB = individualRepository
                .findByPassportIDAndPassportSeries(individualDto.getPassportID(), individualDto.getPassportSeries());

        if (individualDB != null) {//todo посмотри как можно переделать
            if ((int) individualDB.getBankAccounts().stream()
                    .filter(bankAccount -> bankAccount.getCurrency().equals(currency)).count() == 0) {
                bankAccountRepository.save(BankAccount.builder()
                        .balance(BigDecimal.ZERO)
                        .IBAN(getIban())
                        .individual(individualDB)
                        .currency(currency)
                        .accountStatus(ACCOUNT_STATUS.ACTIVE)
                        .build());
                return individualConvertor.convert(individualDB);
            }
            return individualConvertor.convert(individualDB);
        }

        Individual newIndividual = individualRepository.save(Individual.builder()
                .name(individualDto.getName().toUpperCase())
                .surname(individualDto.getSurname().toUpperCase())
                .thirdName(individualDto.getThirdName().toUpperCase())
                .passportSeries(individualDto.getPassportSeries().toUpperCase())
                .passportID(individualDto.getPassportID().toUpperCase())
                .address(individualDto.getAddress().toUpperCase())
                .phoneNumber(individualDto.getPhoneNumber())
                .clientStatus(CLIENT_STATUS.GENERAL)
                .build());
        bankAccountRepository.save(BankAccount.builder()
                .balance(BigDecimal.ZERO)
                .IBAN(getIban())
                .individual(newIndividual)
                .currency(currency)
                .accountStatus(ACCOUNT_STATUS.ACTIVE)
                .build());

        return individualConvertor.convert(newIndividual);
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
    public EntityDto createEntity(EntityDto entityDto, CURRENCY currency) {
        Entity entityDB = entityRepository.findByNameAndAddress(entityDto.getName(), entityDto.getAddress());

        if (entityDB != null) {//todo посмотри как можно переделать
            if ((int) entityDB.getBankAccounts().stream()
                    .filter(bankAccount -> bankAccount.getCurrency().equals(currency)).count() == 0) {
                bankAccountRepository.save(BankAccount.builder()
                        .balance(BigDecimal.ZERO)
                        .IBAN(getIban())
                        .entity(entityDB)
                        .currency(currency)
                        .accountStatus(ACCOUNT_STATUS.ACTIVE)
                        .build());
                return entityConvertor.convert(entityDB);
            }
            return entityConvertor.convert(entityDB);
        }


        Entity newEntity = entityRepository.save(Entity.builder()
                .name(entityDto.getName().toUpperCase())
                .address(entityDto.getAddress().toUpperCase())
                .clientStatus(CLIENT_STATUS.GENERAL)
                .phone(entityDto.getPhone())
                .typeOfOwn(entityDto.getTypeOfOwn())
                .build());

        bankAccountRepository.save(BankAccount.builder()
                .balance(BigDecimal.ZERO)
                .IBAN(getIban())
                .entity(newEntity)
                .currency(currency)
                .accountStatus(ACCOUNT_STATUS.ACTIVE)
                .build());
        return entityConvertor.convert(newEntity);
    }

    @Override
    public List<NaturalOfferDto> getNaturalOffers(CURRENCY currency, BigDecimal sum) {
        return naturalOfferRepository.findByCurrency(currency).stream()
                .filter(naturalOffer -> sum.compareTo(naturalOffer.getSum()) < 0)
                .map(naturalOfferConvertor::convert).collect(Collectors.toList());
    }

    @Override
    public List<LegalOfferDto> getLegalOffers(CURRENCY currency, BigDecimal sum) {
        return legalOfferRepository.findByCurrency(currency).stream()
                .filter(legalOffer -> sum.compareTo(legalOffer.getSum()) < 0)
                .map(legalOfferConvertor::convertor).collect(Collectors.toList());
    }

    @Override
    public void createNaturalContract(IndividualDto individualDto, Long offerId, GuarantorDto guarantorDto, BigDecimal sum) {
        NaturalOffer credit = naturalOfferRepository.findById(offerId).orElseThrow(RuntimeException::new);//todo своя ошибка

        IndividualDto clientDto = createIndividual(individualDto, credit.getCurrency());

        Individual client = individualRepository.findByPassportIDAndPassportSeries(clientDto.getPassportID().toUpperCase(), clientDto.getPassportSeries().toUpperCase());

        if (client.getClientStatus().compareTo(credit.getClientStatus()) <= 0)
            throw new RuntimeException();//todo exeption
        Guarantor guarantor = createGuarantor(guarantorDto);


        String number;
        do {
            number = getCreditNumber();
        } while (naturalCreditRepository.findByNumber(number) == null);


        switch (client.getClientStatus()) {

            case BENEFIT -> {
                naturalCreditRepository.save(NaturalCredit.builder()
                        .sum(sum)
                        .loanTerm(LocalDate.now().plusMonths(credit.getTimeMonth()))//todo тут посмотри как поставить последний день получившегося месяца
                        .status(CREDIT_STATUS.PROCESSING)
                        .clientStatus(client.getClientStatus())
                        .percentageRate((int) Math.round(credit.getPercentageRate() - (credit.getPercentageRate() * 0.1)))
                        .currency(credit.getCurrency())
                        .forfeit(BigDecimal.ZERO)
                        .number(number)
                        .guarantor(null)
                        .individual(client)
                        .build());

            }

            case REGULAR -> {
                if (!guarantor.isAvailable())
                    throw new RuntimeException();//todo exeption, подумай как еще от ифов избавиться

                naturalCreditRepository.save(NaturalCredit.builder()
                        .sum(sum)
                        .loanTerm(LocalDate.now().plusMonths(credit.getTimeMonth()))//todo тут посмотри как поставиьт последний день получившегося месяца
                        .status(CREDIT_STATUS.PROCESSING)
                        .clientStatus(client.getClientStatus())
                        .percentageRate((int) Math.round(credit.getPercentageRate() - (credit.getPercentageRate() * 0.3)))
                        .currency(credit.getCurrency())
                        .forfeit(BigDecimal.ZERO)
                        .number(number)
                        .guarantor(guarantor)
                        .individual(client)
                        .build());

                guarantor.setAvailable(false);
                guarantorRepository.save(guarantor);//todo попробуй эту строчку потом убери, типо оно и так обновится должно
            }

            case VIP -> {
                naturalCreditRepository.save(NaturalCredit.builder()
                        .sum(sum)
                        .loanTerm(LocalDate.now().plusMonths(credit.getTimeMonth()))//todo тут посмотри как поставить последний день получившегося месяца
                        .status(CREDIT_STATUS.PROCESSING)
                        .clientStatus(client.getClientStatus())
                        .percentageRate((int) Math.round(credit.getPercentageRate() - (credit.getPercentageRate() * 0.4)))
                        .currency(credit.getCurrency())
                        .forfeit(BigDecimal.ZERO)
                        .number(number)
                        .guarantor(null)
                        .individual(client)
                        .build());
            }

            default -> {
                if (!guarantor.isAvailable())
                    throw new RuntimeException();//todo exeption, подумай как еще от ифов избавиться

                naturalCreditRepository.save(NaturalCredit.builder()
                        .sum(sum)
                        .loanTerm(LocalDate.now().plusMonths(credit.getTimeMonth()))//todo тут посмотри как поставиьт последний день получившегося месяца
                        .status(CREDIT_STATUS.PROCESSING)
                        .clientStatus(client.getClientStatus())
                        .percentageRate(credit.getPercentageRate())
                        .currency(credit.getCurrency())
                        .forfeit(BigDecimal.ZERO)
                        .number(number)
                        .guarantor(guarantor)
                        .individual(client)
                        .build());

                guarantor.setAvailable(false);
                guarantorRepository.save(guarantor);//todo попробуй эту строчку потом убери, типо оно и так обновится должно
            }

        }
        ;
    }


    @Override
    public void createLegalContract(EntityDto entityDto, Long offerId, BigDecimal sum) {

        LegalOffer credit = legalOfferRepository.findById(offerId).orElseThrow(RuntimeException::new);//todo exception

        EntityDto clientDto = createEntity(entityDto, credit.getCurrency());

        Entity client = entityRepository.findByNameAndAddress(clientDto.getName().toUpperCase(), clientDto.getAddress().toUpperCase());

        String number;
        do {
            number = getCreditNumber();
        } while (naturalCreditRepository.findByNumber(number) == null);

        if (client.getClientStatus().compareTo(credit.getClientStatus()) <= 0)
            throw new RuntimeException();//todo exeption

        switch (client.getClientStatus()) {

            case REGULAR -> {
                legalCreditRepository.save(LegalCredit.builder()
                        .entity(client)
                        .number(number)
                        .forfeit(BigDecimal.ZERO)
                        .status(CREDIT_STATUS.PROCESSING)
                        .clientStatus(client.getClientStatus())
                        .percentageRate((int) Math.round(credit.getPercentageRate() - (credit.getPercentageRate() * 0.25)))
                        .currency(credit.getCurrency())
                        .sum(sum)
                        .loanTerm(LocalDate.now().plusMonths(credit.getTimeMonth()))
                        .build());
            }


            case VIP -> {
                legalCreditRepository.save(LegalCredit.builder()
                        .entity(client)
                        .number(number)
                        .forfeit(BigDecimal.ZERO)
                        .status(CREDIT_STATUS.PROCESSING)
                        .clientStatus(client.getClientStatus())
                        .percentageRate((int) Math.round(credit.getPercentageRate() - (credit.getPercentageRate() * 0.4)))
                        .currency(credit.getCurrency())
                        .sum(sum)
                        .loanTerm(LocalDate.now().plusMonths(credit.getTimeMonth()))
                        .build());


            }

            default -> {
                legalCreditRepository.save(LegalCredit.builder()
                        .entity(client)
                        .number(number)
                        .forfeit(BigDecimal.ZERO)
                        .status(CREDIT_STATUS.PROCESSING)
                        .clientStatus(client.getClientStatus())
                        .percentageRate(credit.getPercentageRate())
                        .currency(credit.getCurrency())
                        .sum(sum)
                        .loanTerm(LocalDate.now().plusMonths(credit.getTimeMonth()))
                        .build());
            }

        }

    }

    @Override
    public void createIndividualCard(IndividualDto individualDto, CARD_TYPE cardType) {

        IndividualDto clientDto = createIndividual(individualDto, CURRENCY.BYN);

        Individual client = individualRepository
                .findByPassportIDAndPassportSeries(clientDto.getPassportID(), clientDto.getPassportSeries());

        bankCardRepository.save(BankCard.builder()
                .individual(client)
                .CVV("create cvv")
                .cardName(client.getName() + " " + client.getSurname())
                .cardNumber("create")
                .cardType(cardType)
                .password("create")
                .validity(LocalDate.now())//todo
                .build()
        );

    }

    private String getCreditNumber() {

        String str = "0123456789";

        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            int number = random.nextInt(10);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

}

