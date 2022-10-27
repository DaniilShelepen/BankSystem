package com.daniil.bank.demo.dal.entity;

import com.daniil.bank.demo.dal.entity.legal.EntityUser;
import com.daniil.bank.demo.dal.entity.natural.IndividualUser;
import com.daniil.bank.demo.enums.LAWSUIT_REASON;
import com.daniil.bank.demo.enums.LAWSUIT_SOLUTION;
import com.daniil.bank.demo.enums.LAWSUIT_STATUS;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Lawsuits {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private LAWSUIT_REASON reason;

    private LAWSUIT_STATUS status;

    private LAWSUIT_SOLUTION solution;

    @ManyToOne
    IndividualUser individualUser;

    @ManyToOne
    EntityUser entityUser;


}
