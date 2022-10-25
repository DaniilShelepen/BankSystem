package com.daniil.bank.demo.services.impl;

import com.daniil.bank.demo.dal.entity.BankAccount;
import com.daniil.bank.demo.dal.entity.legal.EntityUser;
import com.daniil.bank.demo.dal.entity.legal.LegalOffer;
import com.daniil.bank.demo.dal.entity.natural.Guarantor;
import com.daniil.bank.demo.dal.entity.natural.IndividualUser;
import com.daniil.bank.demo.dal.entity.natural.NaturalOffer;
import com.daniil.bank.demo.dal.repository.*;
import com.daniil.bank.demo.dto.*;
import com.daniil.bank.demo.enums.ACCOUNT_STATUS;
import com.daniil.bank.demo.enums.CARD_TYPE;
import com.daniil.bank.demo.enums.CLIENT_STATUS;
import com.daniil.bank.demo.enums.CURRENCY;
import com.daniil.bank.demo.exceptions.ManagerException;
import com.daniil.bank.demo.mapper.*;
import com.daniil.bank.demo.services.CardService;
import com.daniil.bank.demo.services.ContractService;
import com.daniil.bank.demo.services.ManagerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ManagerServiceImpl implements ManagerService {

    private final IndividualRepository individualRepository;
    private final EntityRepository entityRepository;
    private final GuarantorRepository guarantorRepository;
    private final NaturalOfferRepository naturalOfferRepository;
    private final LegalOfferRepository legalOfferRepository;
    private final IndividualConvertor individualConvertor;
    private final NaturalOfferConvertor naturalOfferConvertor;
    private final EntityConvertor entityConvertor;
    private final LegalOfferConvertor legalOfferConvertor;
    private final BankAccountRepository bankAccountRepository;
    private final CardService cardService;
    private final ContractService contractService;

    private final BankAccountConvertor bankAccountConvertor;

    @Override
    public IndividualDto createIndividual(IndividualDto individualDto, CURRENCY currency) {
        IndividualUser individualUserDB = individualRepository
                .findByPassportIDAndPassportSeries(individualDto.getPassportID(), individualDto.getPassportSeries());

        if (individualUserDB != null) {//todo посмотри как можно переделать
            if ((int) individualUserDB.getBankAccounts().stream()
                    .filter(bankAccount -> bankAccount.getCurrency().equals(currency)).count() == 0) {
                bankAccountRepository.save(BankAccount.builder()
                        .balance(BigDecimal.ZERO)
                        .IBAN(getIban())
                        .individualUser(individualUserDB)
                        .currency(currency)
                        .accountStatus(ACCOUNT_STATUS.ACTIVE)
                        .build());
                return individualConvertor.convert(individualUserDB);
            }
            return individualConvertor.convert(individualUserDB);
        }


        IndividualUser newIndividualUser = individualRepository.save(IndividualUser.builder()
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
                .individualUser(newIndividualUser)
                .currency(currency)
                .accountStatus(ACCOUNT_STATUS.ACTIVE)
                .build());

        return individualConvertor.convert(newIndividualUser);
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
        EntityUser entityUserDB = entityRepository.findByName(entityDto.getName().toUpperCase());

        if (entityUserDB != null) {//todo посмотри как можно переделать
            if ((int) entityUserDB.getBankAccounts().stream()
                    .filter(bankAccount -> bankAccount.getCurrency().equals(currency)).count() == 0) {
                bankAccountRepository.save(BankAccount.builder()
                        .balance(BigDecimal.ZERO)
                        .IBAN(getIban())
                        .entityUser(entityUserDB)
                        .currency(currency)
                        .accountStatus(ACCOUNT_STATUS.ACTIVE)
                        .build());
                return entityConvertor.convert(entityUserDB);
            }
            return entityConvertor.convert(entityUserDB);
        }


        EntityUser newEntityUser = entityRepository.save(EntityUser.builder()
                .name(entityDto.getName().toUpperCase())
                .address(entityDto.getAddress().toUpperCase())
                .clientStatus(CLIENT_STATUS.GENERAL)
                .phoneNumber(entityDto.getPhone())
                .typeOfOwn(entityDto.getTypeOfOwn())
                .build());

        bankAccountRepository.save(BankAccount.builder()
                .balance(BigDecimal.ZERO)
                .IBAN(getIban())
                .entityUser(newEntityUser)
                .currency(currency)
                .accountStatus(ACCOUNT_STATUS.ACTIVE)
                .build());
        return entityConvertor.convert(newEntityUser);
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

        IndividualUser client = individualRepository
                .findByPassportIDAndPassportSeries(clientDto.getPassportID().toUpperCase(), clientDto.getPassportSeries().toUpperCase());

        if (client.getClientStatus().compareTo(credit.getClientStatus()) <= 0)
            throw new RuntimeException();//todo exception
        Guarantor guarantor = createGuarantor(guarantorDto);


        contractService.individualContract(client, credit, guarantor, sum);
    }


    @Override
    public void createLegalContract(EntityDto entityDto, Long offerId, BigDecimal sum) {

        LegalOffer credit = legalOfferRepository.findById(offerId).orElseThrow(RuntimeException::new);//todo exception

        EntityDto clientDto = createEntity(entityDto, credit.getCurrency());

        EntityUser client = entityRepository.findByName(clientDto.getName().toUpperCase());

        contractService.legalContract(client, credit, sum);

    }

    @Override
    public void createEntityCard(EntityDto entityDto, IndividualDto individualDto, CARD_TYPE cardType, Integer bankAccNum) {

        EntityDto client1Dto = createEntity(entityDto, CURRENCY.BYN);

        EntityUser client1 = entityRepository.findByName(client1Dto.getName().toUpperCase());

        IndividualDto client2Dto = createIndividual(individualDto, CURRENCY.BYN);

        IndividualUser client2 = individualRepository
                .findByPassportIDAndPassportSeries(client2Dto.getPassportID(), client2Dto.getPassportSeries());

        cardService.createEntityCard(client1, client2, cardType, bankAccNum - 1);


    }

    @Override
    public void createIndividualCard(IndividualDto individualDto, CARD_TYPE cardType, Integer bankAccNum) {
        IndividualDto clientDto = createIndividual(individualDto, CURRENCY.BYN);

        IndividualUser client = individualRepository
                .findByPassportIDAndPassportSeries(clientDto.getPassportID(), clientDto.getPassportSeries());

        cardService.createIndividualCard(client, cardType, bankAccNum - 1);

    }

    @Override
    public List<BankAccountDto> getIndividualBankAccList(String passportID, String passportSeries) {

        IndividualUser client = Optional.ofNullable(individualRepository.findByPassportIDAndPassportSeries(passportID.toUpperCase(),
                passportSeries.toUpperCase())).orElseThrow(RuntimeException::new);//todo

        return bankAccountRepository.findAllByIndividualUser(client).stream()
                .map(bankAccountConvertor::convert).collect(Collectors.toList());
    }

    @Override
    public List<BankAccountDto> getEntityBankAccList(String name) {

        EntityUser client = Optional.ofNullable(entityRepository.findByName(name.toUpperCase()))
                .orElseThrow(RuntimeException::new);//todo

        return bankAccountRepository.findAllByEntityUser(client).stream().map(bankAccountConvertor::convert)
                .collect(Collectors.toList());
    }

    @Override
    public String getIndividualINFO(String passportID, String passportSeries) {

        IndividualUser client = Optional.ofNullable(individualRepository
                        .findByPassportIDAndPassportSeries(passportID.toUpperCase(), passportSeries.toUpperCase()))
                .orElseThrow(RuntimeException::new);//todo


        return client.getName() + " " + client.getSurname() + " " + client.getThirdName() + "\n" +
                client.getPassportSeries() + " " + client.getPassportID() + "\n" + client.getPhoneNumber();
    }

    @Override
    public String getEntityINFO(String name) {

        EntityUser entityUser = Optional.ofNullable(entityRepository.findByName(name.toUpperCase())).orElseThrow(() -> new ManagerException("No such Entity found"));

        return entityUser.getName() + "\n" + entityUser.getAddress() + "\n" + entityUser.getPhoneNumber();
    }

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
}

