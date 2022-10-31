package com.daniil.bank.demo.dal.entity.legal;

import com.daniil.bank.demo.dal.entity.Guarantor;
import com.daniil.bank.demo.dal.entity.Payment;
import com.daniil.bank.demo.dal.entity.Pledge;
import com.daniil.bank.demo.dal.entity.role.Manager;
import com.daniil.bank.demo.enums.CLIENT_STATUS;
import com.daniil.bank.demo.enums.CREDIT_STATUS;
import com.daniil.bank.demo.enums.CURRENCY;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name= "legal_credit")
public class LegalCredit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    private CREDIT_STATUS status;
    private String number;
    @Column(name = "sum")
    private double sum;
    private double monthlyPayment;
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate loanTerm;//срок до какого должен быть выплачен кредит
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @Column(name = "registration_date")
    private LocalDate registration_date;
    private double percentageRate;// процентная ставка
    @Enumerated(EnumType.STRING)
    private CURRENCY currency;//валюта
    private double forfeit;//штраф/
    //   private boolean fixedInterestRate;//фиксированная процентная ставка

    @ManyToOne
    private Pledge pledge;

    @ManyToOne
    private EntityUser entityUser;


    @OneToMany(mappedBy = "legalCredit")
    List<Payment> paymentList;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "last_modified_by")
    //@LastModifiedBy
    private Manager manager;
}
