package com.daniil.bank.demo.dal.entity;


import com.daniil.bank.demo.dal.entity.legal.LegalCredit;
import com.daniil.bank.demo.dal.entity.natural.NaturalCredit;
import com.daniil.bank.demo.enums.CURRENCY;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Pledge {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String estate;// имущество под залог
    private double approximateCost; //примерная цена залога
    private CURRENCY currency;
    private boolean available;


    @OneToMany(mappedBy = "pledge", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private List<NaturalCredit> naturalCredits;


    @OneToMany(mappedBy = "pledge", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private List<LegalCredit> legalCredits;

}
