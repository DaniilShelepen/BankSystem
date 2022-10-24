package com.daniil.bank.demo.dal.entity;

import com.daniil.bank.demo.dal.entity.legal.EntityUser;
import com.daniil.bank.demo.dal.entity.natural.IndividualUser;
import com.daniil.bank.demo.enums.OPERATION_STATUS;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "individual_id")
    private IndividualUser individualUser;

    @ManyToOne
    @JoinColumn(name = "entity_id")
    private EntityUser entityUser;


    private String description;
    private BigDecimal sum;
    private Date date;
    @Enumerated(EnumType.STRING)
    private OPERATION_STATUS operation_status;

    @ManyToOne
    BankAccount bankAccount;
}
