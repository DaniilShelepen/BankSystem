package com.daniil.bank.demo.services.impl;

import com.daniil.bank.demo.dal.entity.BankCard;
import com.daniil.bank.demo.dal.entity.legal.Entity;
import com.daniil.bank.demo.dal.entity.natural.Individual;
import com.daniil.bank.demo.dal.repository.BankCardRepository;
import com.daniil.bank.demo.enums.CARD_TYPE;
import com.daniil.bank.demo.services.CardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class CardServiceImpl implements CardService {

    private final BankCardRepository bankCardRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public String createIndividualCard(Individual individual, CARD_TYPE cardType, Integer bankAccNumber) {

        String CVV = getRandomNumber(3);
        String password = getRandomNumber(4);

        switch (cardType) {

            case VISA -> {


                bankCardRepository.save(BankCard.builder()
                        .individual(individual)
                        .CVV(bCryptPasswordEncoder.encode(CVV))
                        .cardName(individual.getName().toUpperCase() + " " + individual.getSurname().toUpperCase())
                        .cardNumber(generateCardNum(CARD_TYPE.VISA))//todo можешь посидеть поискать как правильно генерировать
                        .cardType(cardType)
                        .password(bCryptPasswordEncoder.encode(password))
                        .validity(LocalDate.now().plusMonths(48L))
                        .corporate(false)
                        .bankAccount(individual.getBankAccounts().get(bankAccNumber - 1))
                        .build());

            }

            case MAESTRO -> {
                bankCardRepository.save(BankCard.builder()
                        .individual(individual)
                        .CVV(bCryptPasswordEncoder.encode(CVV))
                        .cardName(individual.getName().toUpperCase() + " " + individual.getSurname().toUpperCase())
                        .cardNumber(generateCardNum(CARD_TYPE.MAESTRO))
                        .cardType(cardType)
                        .password(bCryptPasswordEncoder.encode(password))
                        .validity(LocalDate.now().plusMonths(24L))
                        .bankAccount(individual.getBankAccounts().get(bankAccNumber - 1))
                        .corporate(false)
                        .build());

            }

            case MASTERCARD -> {
                bankCardRepository.save(BankCard.builder()
                        .individual(individual)
                        .CVV(bCryptPasswordEncoder.encode(CVV))
                        .cardName(individual.getName().toUpperCase() + " " + individual.getSurname().toUpperCase())
                        .cardNumber(generateCardNum(CARD_TYPE.MASTERCARD))
                        .cardType(cardType)
                        .password(bCryptPasswordEncoder.encode(password))
                        .validity(LocalDate.now().plusMonths(36L))
                        .bankAccount(individual.getBankAccounts().get(bankAccNumber - 1))
                        .corporate(false)
                        .build());
            }
            default -> throw new RuntimeException();//todo

        }

        return "CVV: " + CVV + " PASSWORD: " + password + "\n USER " + individual.getName().toUpperCase() + " "
                + individual.getSurname().toUpperCase() + " " + individual.getThirdName().toUpperCase();
    }

    @Override
    public String createEntityCard(Entity entity, Individual individual, CARD_TYPE cardType, Integer bankAccNumber) {

        String CVV = getRandomNumber(3);
        String password = getRandomNumber(4);

        switch (cardType) {

            case VISA -> {

                bankCardRepository.save(BankCard.builder()
                        .entity(entity)
                        .individual(individual)
                        .CVV(bCryptPasswordEncoder.encode(CVV))
                        .cardName(individual.getName().toUpperCase() + " " + individual.getSurname().toUpperCase())
                        .cardNumber(generateCardNum(CARD_TYPE.VISA))//todo можешь посидеть поискать как правильно генерировать
                        .cardType(cardType)
                        .password(bCryptPasswordEncoder.encode(password))
                        .validity(LocalDate.now().plusMonths(48L))
                        .bankAccount(entity.getBankAccounts().get(bankAccNumber - 1))
                        .corporate(true)
                        .build());

            }

            case MAESTRO -> {
                bankCardRepository.save(BankCard.builder()
                        .entity(entity)
                        .individual(individual)
                        .CVV(bCryptPasswordEncoder.encode(CVV))
                        .cardName(individual.getName().toUpperCase() + " " + individual.getSurname().toUpperCase())
                        .cardNumber(generateCardNum(CARD_TYPE.MAESTRO))
                        .cardType(cardType)
                        .password(bCryptPasswordEncoder.encode(password))
                        .validity(LocalDate.now().plusMonths(24L))
                        .bankAccount(entity.getBankAccounts().get(bankAccNumber - 1))
                        .corporate(true)
                        .build());

            }

            case MASTERCARD -> {
                bankCardRepository.save(BankCard.builder()
                        .entity(entity)
                        .individual(individual)
                        .CVV(bCryptPasswordEncoder.encode(CVV))
                        .cardName(individual.getName().toUpperCase() + " " + individual.getSurname().toUpperCase())
                        .cardNumber(generateCardNum(CARD_TYPE.MASTERCARD))
                        .cardType(cardType)
                        .password(bCryptPasswordEncoder.encode(password))
                        .validity(LocalDate.now().plusMonths(36L))
                        .bankAccount(entity.getBankAccounts().get(bankAccNumber - 1))
                        .corporate(true)
                        .build());
            }
            default -> throw new RuntimeException();//todo

        }

        return "CVV: " + CVV + " PASSWORD: " + password + "\n USER " + individual.getName().toUpperCase() + " "
                + individual.getSurname().toUpperCase() + " " + individual.getThirdName().toUpperCase();
    }


    private String getRandomNumber(int bound) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bound; i++) {
            int number = random.nextInt(10);
            sb.append(number);
        }
        return sb.toString();
    }

    private String generateCardNum(CARD_TYPE cardType) {

        String cardNumber;

        switch (cardType) {

            case VISA -> {
                do {
                    cardNumber = "4255" + getRandomNumber(12);
                } while (bankCardRepository.findByCardTypeAndCardNumber(CARD_TYPE.VISA, cardNumber) != null);

                return cardNumber;
            }
            case MAESTRO -> {
                do {
                    cardNumber = "6212" + getRandomNumber(12);
                } while (bankCardRepository.findByCardTypeAndCardNumber(CARD_TYPE.VISA, cardNumber) != null);

                return cardNumber;
            }
            case MASTERCARD -> {
                do {
                    cardNumber = "5514" + getRandomNumber(12);
                } while (bankCardRepository.findByCardTypeAndCardNumber(CARD_TYPE.VISA, cardNumber) != null);

                return cardNumber;
            }
            default -> throw new RuntimeException();//todo
        }
    }

}
