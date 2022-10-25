package com.daniil.bank.demo;

import com.daniil.bank.demo.dal.entity.natural.NaturalCredit;
import com.daniil.bank.demo.dal.repository.IndividualRepository;
import com.daniil.bank.demo.dal.repository.NaturalCreditRepository;
import com.daniil.bank.demo.dto.IndividualDto;
import com.daniil.bank.demo.enums.*;
import com.daniil.bank.demo.services.IndividualService;
import com.daniil.bank.demo.services.ManagerService;
import com.daniil.bank.demo.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootTest
@Slf4j
class BankApplicationTests {

    @Autowired
    NaturalCreditRepository naturalCreditRepository;

    @Autowired
    ManagerService managerService;

    @Autowired
    UserService userService;

    @Autowired
    IndividualService individualService;

    @Autowired
    IndividualRepository individualRepository;



    @Test
    @Order(1)
    void creatingBankIndividualUserAndCard() {
        managerService.createIndividual(IndividualDto.builder()
                .name("DANIIL")
                .surname("SHELEPEN")
                .thirdName("ME")
                .passportSeries("BM")
                .passportID("2274003")
                .address("MY_ADRESS")
                .phoneNumber("+375445873642")
                .build(), CURRENCY.BYN);
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
    @Order(2)
    void creatingUserAccount() {

        userService.createAccount("+375445873642", "asds", ROLE.INDIVIDUAL_ROLE);

    }


    @Test
    @Order(3)
    void NaturalCreditExecute() {


        naturalCreditRepository.save(NaturalCredit.builder()
                .sum(BigDecimal.valueOf(15000))
                .loanTerm(LocalDate.now().plusMonths(12))//todo тут посмотри как поставить последний день получившегося месяца
                .status(CREDIT_STATUS.REGISTERED)
                .clientStatus(CLIENT_STATUS.GENERAL)
                .percentageRate(15)
                .currency(CURRENCY.BYN)
                .forfeit(BigDecimal.ZERO)
                .number("12300942544190BYN")
                .guarantor(null)
                .individualUser(individualRepository.findByPhoneNumber("+375445873642"))
                .build());


    }






    @Test
    void testPay() {

        // individualService.creditPay();
    }


    @Test
    void calculatingMonthPayment() {
        BigDecimal sum = BigDecimal.valueOf(15783);

        BigDecimal a = sum.divide(BigDecimal.valueOf(10L));

        log.info(a.toString());

    }

}
