package com.daniil.bank.demo;

import com.daniil.bank.demo.dal.repository.BankAccountRepository;
import com.daniil.bank.demo.dal.repository.BankCardRepository;
import com.daniil.bank.demo.dal.repository.IndividualRepository;
import com.daniil.bank.demo.dto.IndividualDto;
import com.daniil.bank.demo.dto.NaturalOfferDto;
import com.daniil.bank.demo.enums.CARD_TYPE;
import com.daniil.bank.demo.enums.CLIENT_STATUS;
import com.daniil.bank.demo.enums.CURRENCY;
import com.daniil.bank.demo.security.PasswordEncoderConfig;
import com.daniil.bank.demo.services.CardService;
import com.daniil.bank.demo.services.ManagerService;
import com.daniil.bank.demo.services.OfferService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Slf4j
public class OffersTests {
    @Autowired
    OfferService offerService;
    @Autowired
    ManagerService managerService;

    @Autowired
    BankAccountRepository bankAccountRepository;

    @Autowired
    IndividualRepository individualRepository;

    @Autowired
    BankCardRepository bankCardRepository;

    @Autowired
    CardService cardService;

    @Test
    void createOffers() {
        //naturalOffer
        offerService.createNaturalOffer(NaturalOfferDto.builder()
                .sum(BigDecimal.valueOf(10000.0))
                .currency(CURRENCY.BYN)
                .client_status(CLIENT_STATUS.GENERAL)
                .percentageRate(14)
                .description("Приобретая данный кредит ты хуй высунешь хуй со своей задницы, только для граждан РБ." +
                        "на туалет и биохимию крови кредит расчитан!")
                .time("2 years")
                .available(true)
                .build());
//        offersService.refactorNaturalOffer(1,NaturalOffersDto.builder()
//                .credit(BigDecimal.valueOf(12000.0))
//                .currency(CURRENCY.BYN)
//                .client_status(CLIENT_STATUS.GENERAL)
//                .percentageRate(14)
//                .description("Приобретая данный кредит ты хуй высунешь хуй со своей задницы, только для граждан РБ." +
//                        "на туалет и биохимию крови кредит расчитан!")
//                .available(true)
//                .build());
        offerService.deleteNaturalOffer(3L);
    }

    @Test
    void managerService() {
        IndividualDto in = managerService.createIndividual(IndividualDto.builder()
                .name("pysi")
                .surname("sss")
                .thirdName("asd")
                .passportSeries("sa")
                .passportID("1234567")
                .address("asd")
                .phoneNumber("1234567890123")
                .build(), CURRENCY.USD);

        IndividualDto in2 = managerService.createIndividual(IndividualDto.builder()
                .name("pysi")
                .surname("sss")
                .thirdName("asd")
                .passportSeries("sa")
                .passportID("1234567")
                .address("asd")
                .phoneNumber("1234567890123")
                .build(), CURRENCY.BYN);


        //     individualRepository.deleteByPassportIDAndPassportSeries("1234567","sa".toUpperCase());

//        bankAccountRepository.save(BankAccount.builder()
//                .balance(BigDecimal.ZERO)
//                .IBAN(new Iban.Builder()
//                        .countryCode(CountryCode.BY)
//                        .bankCode("1707")
//                        .buildRandom().toString())
//                .currency(CURRENCY.USD)
//                .individual(in)
//                .build());


    }

    @Test
    void statusTest() {
        IndividualDto in = managerService.createIndividual(IndividualDto.builder()
                .name("pysi")
                .surname("sss")
                .thirdName("asd")
                .passportSeries("sa")
                .passportID("1234567")
                .address("asd")
                .phoneNumber("1234567890123")
                .build(), CURRENCY.USD);


        log.info(String.valueOf(individualRepository.findByPassportIDAndPassportSeries("1234567", "SA").getBankAccounts()));

        log.info(cardService.createIndividualCard(individualRepository.findByPassportIDAndPassportSeries("1234567", "SA"), CARD_TYPE.VISA, 1));

        log.info(cardService.createIndividualCard(individualRepository.findByPassportIDAndPassportSeries("1234567", "SA"), CARD_TYPE.MASTERCARD, 1));


    }


    @Autowired
    PasswordEncoderConfig encoderConfiguration;


    @Test
    void iban() {
        //  log.info(String.valueOf(CLIENT_STATUS.GENERAL.compareTo(CLIENT_STATUS.GENERAL) <= 0));
        //log.info(new BCryptPasswordEncoder().encode("aaaaaa"));


//        bankCardRepository.save(BankCard.builder()
//                .individual(null)
//                .CVV("123")
//                .cardName("aaaa")
//                .cardNumber("123")
//                .cardType(CARD_TYPE.VISA)
//                .password(encoderConfiguration.passwordEncoder().encode("2345"))
//                .validity(LocalDate.now().plusMonths(48L))
//                .coorp(true)
//                .build());

//        Optional<BankCard> bankCard = bankCardRepository.findById(1L);
//
//        log.info(String.valueOf(bankCard.get().getValidity()));


        List<Integer> arr = new ArrayList<>(List.of(1, 2, 3, 4));
        log.info(String.valueOf(arr.get(1)));

    }

}
