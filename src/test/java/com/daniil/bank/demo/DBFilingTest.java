package com.daniil.bank.demo;

import com.daniil.bank.demo.dal.entity.Lawsuit;
import com.daniil.bank.demo.dal.entity.Payment;
import com.daniil.bank.demo.dal.repository.*;
import com.daniil.bank.demo.dto.*;
import com.daniil.bank.demo.enums.*;
import com.daniil.bank.demo.services.ManagerService;
import com.daniil.bank.demo.services.OfferService;
import com.daniil.bank.demo.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
@Slf4j
public class DBFilingTest {


    @Autowired
    ManagerService managerService;
    @Autowired
    UserService userService;
    @Autowired
    OfferService offerService;

    @Autowired
    LawsuitRepository lawsuitRepository;

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    IndividualRepository individualRepository;

    @Autowired
    NaturalCreditRepository naturalCreditRepository;

    @Autowired
    BankAccountRepository bankAccountRepository;

    @Test
    void DBFiling() {

        /** Individual Entity **/

        IndividualDto daniilIndividual = IndividualDto.builder()
                .name("Даниил")
                .surname("Шелепень")
                .thirdName("я")
                .passportSeries("BM")
                .passportID("2274003")
                .address("адрес")
                .phoneNumber("+375445873642")
                .build();

        IndividualDto nikitaIndividual = IndividualDto.builder()
                .name("никита якушенко")
                .surname("yakushenko")
                .thirdName("петрович")
                .passportSeries("BM")
                .passportID("5438916")
                .address("никитин адрес")
                .phoneNumber("+375295763357")
                .build();

        IndividualDto evgeniyIndividual = IndividualDto.builder()
                .name("евгений")
                .surname("косяков")
                .thirdName("дверев")
                .passportSeries("BM")
                .passportID("5576319")
                .address("женя адрес")
                .phoneNumber("+375295466972")
                .build();

        IndividualDto prideWorkerIndividual = IndividualDto.builder()
                .name("константин")
                .surname("прайдовский")
                .thirdName("завод")
                .passportSeries("BM")
                .passportID("5551319")
                .address("костя адрес")
                .phoneNumber("+375295457132")
                .build();

        EntityDto naftanEntity = EntityDto.builder()
                .name("нафтан")
                .address("нафтановский адрес")
                .phone("+375295761397")
                .typeOfOwn("завод, станки и еще что-то")
                .build();

        EntityDto epamEntity = EntityDto.builder()
                .name("епам")
                .address("епамовский адрес")
                .phone("+375296128762")
                .typeOfOwn("офис, компы и еще что-то")
                .build();

        EntityDto prideEntity = EntityDto.builder()
                .name("прайд")
                .address("прайдовский адрес")
                .phone("+375295697258")
                .typeOfOwn("завод, станки, тесла, мерс и тд.")
                .build();

        managerService.createIndividual(daniilIndividual, CURRENCY.USD);

        managerService.createIndividual(nikitaIndividual, CURRENCY.BYN);

        managerService.createIndividual(evgeniyIndividual, CURRENCY.EUR);

        managerService.createEntity(naftanEntity, CURRENCY.USD);

        managerService.createEntity(epamEntity, CURRENCY.USD);

        managerService.createEntity(prideEntity, CURRENCY.USD);
        /** **/


        /** Creating cards **/
        managerService.createIndividualCard(daniilIndividual
                , CARD_TYPE.VISA, 1);

        managerService.createIndividualCard(nikitaIndividual
                , CARD_TYPE.MAESTRO, 1);

        managerService.createIndividualCard(evgeniyIndividual
                , CARD_TYPE.VISA, 1);

        managerService.createEntityCard(prideEntity, prideWorkerIndividual,
                CARD_TYPE.VISA, 1);

        /** **/

        userService.createAccount("+375445873642", "asds", ROLE.INDIVIDUAL_ROLE);

        /** **/


        /** offers **/


        NaturalOfferDto naturalOffer1 = NaturalOfferDto.builder()
                .percentageRate(15.0)
                .description("bla bla bla")
                .client_status(CLIENT_STATUS.GENERAL)
                .available(true)
                .currency(CURRENCY.BYN)
                .sum(25_000.0)
                .months(36L)
                .build();

        NaturalOfferDto naturalOffer2 = NaturalOfferDto.builder()
                .percentageRate(12.0)
                .description("bla2 bla2 bla2")
                .client_status(CLIENT_STATUS.GENERAL)
                .available(true)
                .currency(CURRENCY.BYN)
                .sum(18_000.0)
                .months(24L)
                .build();

        LegalOfferDto legalOffer1 = LegalOfferDto.builder()
                .percentageRate(8.0)
                .description("blaL blaL blaL")
                .client_status(CLIENT_STATUS.GENERAL)
                .available(true)
                .currency(CURRENCY.BYN)
                .sum(95_000.0)
                .months(60L)
                .build();

        LegalOfferDto legalOffer2 = LegalOfferDto.builder()
                .percentageRate(8.0)
                .description("blaL2 blaL2 blaL2")
                .client_status(CLIENT_STATUS.GENERAL)
                .available(true)
                .currency(CURRENCY.BYN)
                .sum(45_000.0)
                .months(48L)
                .build();

        offerService.createNaturalOffer(naturalOffer1);
        offerService.createNaturalOffer(naturalOffer2);
        offerService.createLegalOffer(legalOffer1);
        offerService.createLegalOffer(legalOffer2);

/** **/


/** guarant **/


/** **/
        GuarantorDto g1 = GuarantorDto.builder()
                .name("Влада")
                .surname("чижик")
                .passportID("2579632")
                .passportSeries("BM")
                .estate("много много деняк")
                .approximateCost(5545645512.0)
                .currency(CURRENCY.BYN)
                .address("Новополоцк")
                .build();

        GuarantorDto g2 = GuarantorDto.builder()
                .name("Томас")
                .surname("кот")
                .passportID("2578632")
                .passportSeries("BM")
                .estate("много много корма")
                .approximateCost(5545645512.0)
                .currency(CURRENCY.BYN)
                .address("Новополоцк")
                .build();

/** contracts **/

        managerService.createNaturalContract(daniilIndividual, 1L, g1, 19_500.0);

        managerService.createNaturalContract(nikitaIndividual, 2L, g2, 15_500.0);

        managerService.createLegalContract(prideEntity, 1L, 40_000);
/** **/


    }

    @Test
    void test() {

        lawsuitRepository.save(Lawsuit.builder()
                .individualUser(individualRepository.findByPhoneNumber("+375295763357"))
                .cost(150.0)
                .startTime(LocalDate.now())
                .reason(LAWSUIT_REASON.NON_PAYMENT)
                .build()
        );


        paymentRepository.save(Payment.builder()
                .naturalCredit(naturalCreditRepository.findByNumber("18723131500707957388"))
                .sum(12.0)
                .individualUser(individualRepository.findByPhoneNumber("+375295763357"))
                .date(LocalDateTime.now())
                .build()
        );


        paymentRepository.save(Payment.builder()
                .naturalCredit(naturalCreditRepository.findByNumber("18723131500707957388"))
                .sum(115.0)
                .individualUser(individualRepository.findByPhoneNumber("+375295763357"))
                .date(LocalDateTime.of(2022, 9, 15, 18, 23))
                .build()
        );

    }

    @Test
    void queryTest() {
        log.info(String.valueOf(LocalDate.now()));
        log.info(naturalCreditRepository.banksTotalWithdrawal(LocalDate.now()));//"2022-11"

    }

}
