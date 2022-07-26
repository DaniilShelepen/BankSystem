package com.daniil.bank.demo.dal.entity.natural;

import com.daniil.bank.demo.finance.CLIENT_STATUS;
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
    @Enumerated(EnumType.STRING)
    private CLIENT_STATUS client_status;

    @OneToMany(mappedBy = "individual")
    List<NaturalCredit> naturalCreditList;
}
