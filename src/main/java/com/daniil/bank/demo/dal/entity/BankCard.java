package com.daniil.bank.demo.dal.entity;

import com.daniil.bank.demo.dal.entity.legal.Entity;
import com.daniil.bank.demo.dal.entity.natural.Individual;
import com.daniil.bank.demo.enums.CARD_TYPE;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@javax.persistence.Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class BankCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String cardName;
    private String cardNumber;
    @DateTimeFormat(pattern = "MM.YY")
    private LocalDate validity;
    private String CVV;
    private String password;
    @Enumerated(EnumType.STRING)
    CARD_TYPE cardType;

    @OneToOne
    Individual individual;
    @ManyToOne
    Entity entity;
}
