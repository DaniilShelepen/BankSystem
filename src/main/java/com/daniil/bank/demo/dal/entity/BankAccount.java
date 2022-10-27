package com.daniil.bank.demo.dal.entity;

import com.daniil.bank.demo.dal.entity.legal.EntityUser;
import com.daniil.bank.demo.dal.entity.natural.IndividualUser;
import com.daniil.bank.demo.dal.entity.role.Manager;
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

@Entity
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
    private double balance;
    @Enumerated(EnumType.STRING)
    private CURRENCY currency;
    @Enumerated(EnumType.STRING)
    private ACCOUNT_STATUS accountStatus;

    @OneToMany(mappedBy = "bankAccount", cascade = CascadeType.ALL)
    List<BankCard> bankCard;

    @ManyToOne
    IndividualUser individualUser;

    @ManyToOne
    EntityUser entityUser;

    @OneToMany(mappedBy = "bankAccount")
    List<Operation> operations;

    @ManyToOne(fetch = FetchType.EAGER)
    //@LastModifiedBy
    private Manager manager;
}
