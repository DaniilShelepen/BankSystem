package com.daniil.bank.demo.dal.entity.legal;

import com.daniil.bank.demo.finance.CLIENT_STATUS;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@javax.persistence.Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;


    private String name;
    private String typeOfOwn;
    private String address;
    private String phone;
    @Enumerated(EnumType.STRING)
    private CLIENT_STATUS client_status;

    @OneToMany(mappedBy = "entity")
    List<LegalCredit> legalCreditList;

}
