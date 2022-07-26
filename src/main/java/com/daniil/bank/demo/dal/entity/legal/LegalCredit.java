package com.daniil.bank.demo.dal.entity.legal;

import com.daniil.bank.demo.finance.CLIENT_STATUS;
import com.daniil.bank.demo.finance.CREDIT_STATUS;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

@javax.persistence.Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class LegalCredit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private CREDIT_STATUS status;
    @Enumerated(EnumType.STRING)
    private CLIENT_STATUS client_status;
    private BigDecimal amount;

    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate loanTerm;//срок до какого должен быть выплачен кредит
    private Integer percentageRate;// процентная ставка
    private Currency currency;//валюта
    private BigDecimal forfeit;//штраф

 //   private boolean fixedInterestRate;//фиксированная процентная ставка

    @ManyToOne
    private Entity entity;
}

