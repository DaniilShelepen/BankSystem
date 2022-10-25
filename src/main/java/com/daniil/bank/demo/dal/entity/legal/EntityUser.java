package com.daniil.bank.demo.dal.entity.legal;

import com.daniil.bank.demo.dal.entity.BankAccount;
import com.daniil.bank.demo.dal.entity.BankCard;
import com.daniil.bank.demo.dal.entity.Lawsuits;
import com.daniil.bank.demo.dal.entity.Payment;
import com.daniil.bank.demo.dal.entity.role.Manager;
import com.daniil.bank.demo.dal.entity.role.User;
import com.daniil.bank.demo.enums.CLIENT_STATUS;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class EntityUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    private String name;
    private String typeOfOwn;
    private String address;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private CLIENT_STATUS clientStatus;

    @OneToMany(mappedBy = "entityUser")
    List<LegalCredit> legalCreditList;

    @OneToMany(mappedBy = "entityUser", fetch = FetchType.EAGER)
    List<BankAccount> bankAccounts;

    @OneToMany(mappedBy = "entityUser")
    List<BankCard> bankCards;

    @OneToOne
    User user;

    @OneToMany(mappedBy = "entityUser")
    List<Lawsuits> lawsuits;

    @OneToMany(mappedBy = "entityUser")
    List<Payment> payments;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "last_modified_by")
    //@LastModifiedBy
    private Manager manager;
}
