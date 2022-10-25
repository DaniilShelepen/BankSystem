package com.daniil.bank.demo.services.impl;

import com.daniil.bank.demo.dal.entity.Payment;
import com.daniil.bank.demo.dal.entity.legal.EntityUser;
import com.daniil.bank.demo.dal.entity.legal.LegalCredit;
import com.daniil.bank.demo.dal.entity.natural.IndividualUser;
import com.daniil.bank.demo.dal.entity.natural.NaturalCredit;
import com.daniil.bank.demo.dal.repository.PaymentRepository;
import com.daniil.bank.demo.services.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Override
    public void createIndividualPayment(IndividualUser individualUser, BigDecimal sum, NaturalCredit naturalCredit) {

        paymentRepository.save(Payment.builder()
                .individualUser(individualUser)
                .sum(sum)
                .naturalCredit(naturalCredit)
                .build()
        );

    }

    @Override
    public void createEntityPayment(EntityUser entityUser, BigDecimal sum, LegalCredit legalCredit) {

        paymentRepository.save(Payment.builder()
                .entityUser(entityUser)
                .sum(sum)
                .legalCredit(legalCredit)
                .build()
        );

    }
}
