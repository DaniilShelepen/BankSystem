package com.daniil.bank.demo.dal.entity.legal;

import com.daniil.bank.demo.dal.entity.BankAccount;
import com.daniil.bank.demo.enums.CLIENT_STATUS;
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
    private Long id;


    private String name;
    private String typeOfOwn;
    private String address;
    private String phone;
    @Enumerated(EnumType.STRING)
    private CLIENT_STATUS clientStatus;

    @OneToMany(mappedBy = "entity")
    List<LegalCredit> legalCreditList;

    @OneToMany(mappedBy = "entity", fetch = FetchType.EAGER)
    List<BankAccount> bankAccounts;
}
