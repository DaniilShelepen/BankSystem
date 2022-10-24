package com.daniil.bank.demo.services.impl;

import com.daniil.bank.demo.dal.entity.legal.EntityUser;
import com.daniil.bank.demo.dal.entity.legal.LegalCredit;
import com.daniil.bank.demo.dal.entity.legal.LegalOffer;
import com.daniil.bank.demo.dal.entity.natural.Guarantor;
import com.daniil.bank.demo.dal.entity.natural.IndividualUser;
import com.daniil.bank.demo.dal.entity.natural.NaturalCredit;
import com.daniil.bank.demo.dal.entity.natural.NaturalOffer;
import com.daniil.bank.demo.dal.repository.GuarantorRepository;
import com.daniil.bank.demo.dal.repository.LegalCreditRepository;
import com.daniil.bank.demo.dal.repository.NaturalCreditRepository;
import com.daniil.bank.demo.enums.CREDIT_STATUS;
import com.daniil.bank.demo.services.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Random;


@Service
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {


    private final NaturalCreditRepository naturalCreditRepository;

    private final GuarantorRepository guarantorRepository;

    private final LegalCreditRepository legalCreditRepository;


    @Override
    public void individualContract(IndividualUser client, NaturalOffer credit, Guarantor guarantor, BigDecimal sum) {


        String number;
        do {
            number = getRandomNumber(20);
        } while (naturalCreditRepository.findByNumber(number) != null);


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
                        .individualUser(client)
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
                        .individualUser(client)
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
                        .individualUser(client)
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
                        .individualUser(client)
                        .build());

                guarantor.setAvailable(false);
                guarantorRepository.save(guarantor);//todo попробуй эту строчку потом убери, типо оно и так обновится должно
            }

        }


    }

    @Override
    public void legalContract(EntityUser client, LegalOffer credit, BigDecimal sum) {

        String number;
        do {
            number = getRandomNumber(20);
        } while (naturalCreditRepository.findByNumber(number) != null);

        if (client.getClientStatus().compareTo(credit.getClientStatus()) <= 0)
            throw new RuntimeException();//todo exeption

        switch (client.getClientStatus()) {

            case REGULAR -> {
                legalCreditRepository.save(LegalCredit.builder()
                        .entityUser(client)
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
                        .entityUser(client)
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
                        .entityUser(client)
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


    private String getRandomNumber(int bound) {

        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bound; i++) {
            int number = random.nextInt(10);
            sb.append(number);
        }
        return sb.toString();
    }


}
