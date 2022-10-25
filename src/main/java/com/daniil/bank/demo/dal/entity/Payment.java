package com.daniil.bank.demo.dal.entity;


import com.daniil.bank.demo.dal.entity.legal.EntityUser;
import com.daniil.bank.demo.dal.entity.legal.LegalCredit;
import com.daniil.bank.demo.dal.entity.natural.IndividualUser;
import com.daniil.bank.demo.dal.entity.natural.NaturalCredit;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Payment {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    String number;
    BigDecimal sum;
    LocalDate date;

    @ManyToOne
    EntityUser entityUser;
    @ManyToOne
    IndividualUser individualUser;

    @ManyToOne
    NaturalCredit naturalCredit;

    @ManyToOne
    LegalCredit legalCredit;
}
