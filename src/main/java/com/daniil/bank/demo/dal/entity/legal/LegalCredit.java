package com.daniil.bank.demo.dal.entity.legal;

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

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class LegalCredit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    private CREDIT_STATUS status;
    private String number;
    @Enumerated(EnumType.STRING)
    private CLIENT_STATUS clientStatus;
    private BigDecimal sum;

    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate loanTerm;//срок до какого должен быть выплачен кредит
    private Integer percentageRate;// процентная ставка
    @Enumerated(EnumType.STRING)
    private CURRENCY currency;//валюта
    private BigDecimal forfeit;//штраф/

    //   private boolean fixedInterestRate;//фиксированная процентная ставка

    @ManyToOne
    private EntityUser entityUser;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "last_modified_by")
    //@LastModifiedBy
    private Manager manager;
}
