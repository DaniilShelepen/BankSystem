package com.daniil.bank.demo.dal.entity.natural;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Guarantor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String name;
    private String surname;
    private String thirdName;

    @Size(min = 2, max = 2)
    private String passportSeries;

    @Size(min = 7, max = 7)
    private String passportID;
    private String address;

    @Size(min = 13, max = 13)
    private String phoneNumber;

    private String estate;// имущество под залог
    private BigDecimal approximateCost; //примерная цена залога
    private boolean available;

    @OneToMany(mappedBy = "guarantor", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private List<NaturalCredit> creditList;
}
