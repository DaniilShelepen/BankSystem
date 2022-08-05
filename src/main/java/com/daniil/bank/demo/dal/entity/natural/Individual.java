package com.daniil.bank.demo.dal.entity.natural;

import com.daniil.bank.demo.dal.entity.BankAccount;
import com.daniil.bank.demo.dal.entity.BankCard;
import com.daniil.bank.demo.enums.CLIENT_STATUS;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Individual {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

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
    @Enumerated(EnumType.STRING)
    private CLIENT_STATUS clientStatus;

    @OneToMany(mappedBy = "individual", cascade = CascadeType.ALL)
    List<NaturalCredit> naturalCreditList;
    @OneToMany(mappedBy = "individual", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<BankAccount> bankAccounts;

    @OneToOne(mappedBy = "individual", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    BankCard bankCard;
}
