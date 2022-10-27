package com.daniil.bank.demo;

import com.daniil.bank.demo.dal.entity.BankAccount;
import com.daniil.bank.demo.dal.entity.natural.IndividualUser;
import com.daniil.bank.demo.dal.entity.natural.NaturalCredit;
import com.daniil.bank.demo.dal.repository.BankAccountRepository;
import com.daniil.bank.demo.dal.repository.GuarantorRepository;
import com.daniil.bank.demo.dal.repository.IndividualRepository;
import com.daniil.bank.demo.dal.repository.NaturalCreditRepository;
import com.daniil.bank.demo.dto.GuarantorDto;
import com.daniil.bank.demo.dto.IndividualDto;
import com.daniil.bank.demo.dto.NaturalCreditDto;
import com.daniil.bank.demo.dto.NaturalOfferDto;
import com.daniil.bank.demo.enums.*;
import com.daniil.bank.demo.services.IndividualService;
import com.daniil.bank.demo.services.ManagerService;
import com.daniil.bank.demo.services.OfferService;
import com.daniil.bank.demo.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootTest
@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BankApplicationTests {

    @Autowired
    NaturalCreditRepository naturalCreditRepository;

    @Autowired
    ManagerService managerService;

    @Autowired
    UserService userService;

    @Autowired
    BankAccountRepository bankAccountRepository;

    @Autowired
    IndividualService individualService;

    @Autowired
    IndividualRepository individualRepository;

    @Autowired
    OfferService offerService;

    @Autowired
    GuarantorRepository guarantorRepository;


    @Test
    @Order(1)
    void creatingIndividualUser() {
        managerService.createIndividual(IndividualDto.builder()
                .name("DANIIL")
                .surname("SHELEPEN")
                .thirdName("ME")
                .passportSeries("BM")
                .passportID("2274003")
                .address("MY_ADRESS")
                .phoneNumber("+375445873642")
                .build(), CURRENCY.BYN);

    }


    @Test
    @Order(2)
    void creatingIndividualCard() {

        managerService.createIndividualCard(IndividualDto.builder()
                        .name("DANIIL")
                        .surname("SHELEPEN")
                        .thirdName("ME")
                        .passportSeries("BM")
                        .passportID("2274003")
                        .address("MY_ADRESS")
                        .phoneNumber("+375445873642")
                        .build()
                , CARD_TYPE.VISA, 1);
    }


    @Test
    @Order(3)
    void creatingUserAccount() {
        userService.createAccount("+375445873642", "asds", ROLE.INDIVIDUAL_ROLE);

    }


    @Test
    @Order(4)
    void NaturalCreditExecute() {

        offerService.createNaturalOffer(NaturalOfferDto.builder()
                .percentageRate(15.0)
                .description("bla bla bla")
                .client_status(CLIENT_STATUS.GENERAL)
                .available(true)
                .currency(CURRENCY.BYN)
                .sum(25_000.0)
                .months(36L)
                .build()
        );


        managerService.createNaturalContract(IndividualDto.builder()
                        .name("DANIIL")
                        .surname("SHELEPEN")
                        .thirdName("ME")
                        .passportSeries("BM")
                        .passportID("2274003")
                        .address("MY_ADRESS")
                        .phoneNumber("+375445873642")
                        .build(), 1L,
                GuarantorDto.builder()
                        .name("Vladochka")
                        .passportID("2579632")
                        .passportSeries("BM")
                        .estate("много много деняк")
                        .approximateCost(5545645512.0)
                        .currency(CURRENCY.BYN)
                        .address("Novopolotsk")
                        .build(),
                19_500.0);

    }

    //4255059625417942
//    @Test
//    @Order(5)
//    void testPay() {
//        BankAccount bankAccount = bankAccountRepository.findBankAccountByIBAN("BY6517075444HLWS5OUYSL6Y7XFX");
//        bankAccount.setBalance(BigDecimal.valueOf(99999999));
//        bankAccountRepository.save(bankAccount);
//
//        IndividualUser individualUser = individualRepository.findByPhoneNumber("+375445873642");
//        individualService.creditPay("4255059625417942", "12300942544190BYN", BigDecimal.valueOf(400), individualUser.getId());
//    }


    @Test
    void calculatingCreditSum() {

        double sum = 1500.0;

        long month = 24L;

        double percentageRate = 8.0;

        double monthlyPayment = percentageRate / 12 / 100;


        sum = sum * (monthlyPayment) * (Math.pow(monthlyPayment + 1, month) / (Math.pow(monthlyPayment + 1, month) - 1));

        log.info(String.valueOf(sum));

    }


}
