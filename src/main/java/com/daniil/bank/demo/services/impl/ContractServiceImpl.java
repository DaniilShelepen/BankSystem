package com.daniil.bank.demo.services.impl;

import com.daniil.bank.demo.dal.entity.legal.EntityUser;
import com.daniil.bank.demo.dal.entity.legal.LegalCredit;
import com.daniil.bank.demo.dal.entity.legal.LegalOffer;
import com.daniil.bank.demo.dal.entity.Guarantor;
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

import java.time.LocalDate;
import java.util.Random;


@Service
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {


    private final NaturalCreditRepository naturalCreditRepository;

    private final GuarantorRepository guarantorRepository;

    private final LegalCreditRepository legalCreditRepository;


    @Override
    public void individualContract(IndividualUser client, NaturalOffer credit, Guarantor guarantor, double sum) {//todo посмотри как залог/поручитель


        String number;
        do {
            number = getRandomNumber();
        } while (naturalCreditRepository.findByNumber(number) != null);

        double percentageRate;

        switch (client.getClientStatus()) {
            case BENEFIT -> {

                percentageRate = credit.getPercentageRate() - (credit.getPercentageRate() * 0.15);

                naturalCreditRepository.save(NaturalCredit.builder()
                        .sum(calculationCredit(sum, credit.getTimeMonth(), percentageRate))
                        .monthlyPayment(calculationCredit(sum, credit.getTimeMonth(), percentageRate) / credit.getTimeMonth())
                        .loanTerm(LocalDate.now().plusMonths(credit.getTimeMonth()))//todo тут посмотри как поставить последний день получившегося месяца
                        .status(CREDIT_STATUS.PROCESSING)
                        .clientStatus(client.getClientStatus())
                        .percentageRate(percentageRate)
                        .currency(credit.getCurrency())
                        .forfeit(0.0)
                        .number(number)
                        .guarantor(null)
                        .individualUser(client)
                        .build());

            }

            case REGULAR -> {
                if (!guarantor.isAvailable())
                    throw new RuntimeException();//todo exeption, подумай как еще от ифов избавиться

                percentageRate = credit.getPercentageRate() - (credit.getPercentageRate() * 0.3);
                naturalCreditRepository.save(NaturalCredit.builder()
                        .sum(calculationCredit(sum, credit.getTimeMonth(), percentageRate))
                        .monthlyPayment(calculationCredit(sum, credit.getTimeMonth(), percentageRate) / credit.getTimeMonth())
                        .loanTerm(LocalDate.now().plusMonths(credit.getTimeMonth()))//todo тут посмотри как поставиьт последний день получившегося месяца
                        .status(CREDIT_STATUS.PROCESSING)
                        .clientStatus(client.getClientStatus())
                        .percentageRate(percentageRate)
                        .currency(credit.getCurrency())
                        .forfeit(0.0)
                        .number(number)
                        .guarantor(guarantor)
                        .individualUser(client)
                        .build());

                guarantor.setAvailable(false);
                guarantorRepository.save(guarantor);//todo попробуй эту строчку потом убери, типо оно и так обновится должно
            }

            case VIP -> {
                percentageRate = credit.getPercentageRate() - (credit.getPercentageRate() * 0.5);
                naturalCreditRepository.save(NaturalCredit.builder()
                        .sum(calculationCredit(sum, credit.getTimeMonth(), percentageRate))
                        .monthlyPayment(calculationCredit(sum, credit.getTimeMonth(), percentageRate) / credit.getTimeMonth())
                        .loanTerm(LocalDate.now().plusMonths(credit.getTimeMonth()))//todo тут посмотри как поставить последний день получившегося месяца
                        .status(CREDIT_STATUS.PROCESSING)
                        .clientStatus(client.getClientStatus())
                        .percentageRate(percentageRate)
                        .currency(credit.getCurrency())
                        .forfeit(0.0)
                        .number(number)
                        .guarantor(null)
                        .individualUser(client)
                        .build());
            }

            default -> {
                if (!guarantor.isAvailable())
                    throw new RuntimeException();//todo exeption, подумай как еще от ифов избавиться

                naturalCreditRepository.save(NaturalCredit.builder()
                        .sum(calculationCredit(sum, credit.getTimeMonth(), credit.getPercentageRate()))
                        .monthlyPayment(calculationCredit(sum, credit.getTimeMonth(), credit.getPercentageRate()) / credit.getTimeMonth())
                        .loanTerm(LocalDate.now().plusMonths(credit.getTimeMonth()))//todo тут посмотри как поставиьт последний день получившегося месяца
                        .status(CREDIT_STATUS.PROCESSING)
                        .clientStatus(client.getClientStatus())
                        .percentageRate(credit.getPercentageRate())
                        .currency(credit.getCurrency())
                        .forfeit(0.0)
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
    public void legalContract(EntityUser client, LegalOffer credit, double sum) {

        if (client.getClientStatus().compareTo(credit.getClientStatus()) <= 0)
            throw new RuntimeException();//todo exeption

        String number;
        do {
            number = getRandomNumber();
        } while (naturalCreditRepository.findByNumber(number) != null);

        double percentageRate;

        switch (client.getClientStatus()) {

            case REGULAR -> {
                percentageRate = credit.getPercentageRate() - (credit.getPercentageRate() * 0.25);
                legalCreditRepository.save(LegalCredit.builder()
                        .entityUser(client)
                        .number(number)
                        .forfeit(0.0)
                        .status(CREDIT_STATUS.PROCESSING)
                        .clientStatus(client.getClientStatus())
                        .percentageRate(percentageRate)
                        .currency(credit.getCurrency())
                        .sum(calculationCredit(sum, credit.getTimeMonth(), percentageRate))
                        .monthlyPayment(calculationCredit(sum, credit.getTimeMonth(), percentageRate) / credit.getTimeMonth())
                        .loanTerm(LocalDate.now().plusMonths(credit.getTimeMonth()))
                        .build());
            }


            case VIP -> {
                percentageRate = credit.getPercentageRate() - (credit.getPercentageRate() * 0.6);
                legalCreditRepository.save(LegalCredit.builder()
                        .entityUser(client)
                        .number(number)
                        .forfeit(0.0)
                        .status(CREDIT_STATUS.PROCESSING)
                        .clientStatus(client.getClientStatus())
                        .percentageRate(percentageRate)
                        .currency(credit.getCurrency())
                        .sum(calculationCredit(sum, credit.getTimeMonth(), percentageRate))
                        .monthlyPayment(calculationCredit(sum, credit.getTimeMonth(), percentageRate) / credit.getTimeMonth())
                        .loanTerm(LocalDate.now().plusMonths(credit.getTimeMonth()))
                        .build());


            }

            default -> {
                legalCreditRepository.save(LegalCredit.builder()
                        .entityUser(client)
                        .number(number)
                        .forfeit(0.0)
                        .status(CREDIT_STATUS.PROCESSING)
                        .clientStatus(client.getClientStatus())
                        .percentageRate(credit.getPercentageRate())
                        .currency(credit.getCurrency())
                        .sum(calculationCredit(sum, credit.getTimeMonth(), credit.getPercentageRate()))
                        .monthlyPayment(calculationCredit(sum, credit.getTimeMonth(), credit.getPercentageRate()) / credit.getTimeMonth())
                        .loanTerm(LocalDate.now().plusMonths(credit.getTimeMonth()))
                        .build());
            }

        }
    }


    private static String getRandomNumber() {

        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            int number = random.nextInt(10);
            sb.append(number);
        }
        return sb.toString();
    }

    private static double calculationCredit(double sum, Long months, double percentageRate) {
        double monthlyRate = percentageRate / 12 / 100;
        sum = sum * (monthlyRate) * (Math.pow(monthlyRate + 1, months) / (Math.pow(monthlyRate + 1, months) - 1));
        return sum * months;
    }

}
