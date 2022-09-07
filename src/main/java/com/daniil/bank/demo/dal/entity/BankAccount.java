package com.daniil.bank.demo.dal.entity;

import com.daniil.bank.demo.dal.entity.legal.Entity;
import com.daniil.bank.demo.dal.entity.natural.Individual;
import com.daniil.bank.demo.enums.ACCOUNT_STATUS;
import com.daniil.bank.demo.enums.CURRENCY;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@javax.persistence.Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String IBAN;
    private BigDecimal balance;
    @Enumerated(EnumType.STRING)
    private CURRENCY currency;
    @Enumerated(EnumType.STRING)
    private ACCOUNT_STATUS accountStatus;

    @OneToMany(mappedBy = "bankAccount", cascade = CascadeType.ALL)
    List<BankCard> bankCard;

    @ManyToOne
    Individual individual;

    @ManyToOne
    Entity entity;
}
