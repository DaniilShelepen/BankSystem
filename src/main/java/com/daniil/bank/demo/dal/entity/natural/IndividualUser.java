package com.daniil.bank.demo.dal.entity.natural;

import com.daniil.bank.demo.dal.entity.BankAccount;
import com.daniil.bank.demo.dal.entity.BankCard;
import com.daniil.bank.demo.dal.entity.Lawsuits;
import com.daniil.bank.demo.dal.entity.Payment;
import com.daniil.bank.demo.dal.entity.role.Manager;
import com.daniil.bank.demo.dal.entity.role.User;
import com.daniil.bank.demo.dto.IndividualsAndCardsDto;
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
@NamedNativeQuery(name = "IndividualUser.getIndividualsAndCards",
        query = "SELECT individual_user.\"name\",individual_user.surname,bank_card.card_name,bank_card.card_number\n" +
                "FROM public.individual_user\n" +
                "inner join public.bank_card on individual_user.id = public.bank_card.individual_user_id",
        resultSetMapping = "com.daniil.bank.demo.dto.IndividualsAndCardsDto")
@SqlResultSetMapping(name = "com.daniil.bank.demo.dto.IndividualsAndCardsDto",
        classes = @ConstructorResult(targetClass = IndividualsAndCardsDto.class,
                columns = {@ColumnResult(name = "name"),
                        @ColumnResult(name = "surname"),
                        @ColumnResult(name = "card_name"),
                        @ColumnResult(name = "card_number")
                }))
public class IndividualUser {
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

    @OneToMany(mappedBy = "individualUser", cascade = CascadeType.ALL)
    List<NaturalCredit> naturalCreditList;
    @OneToMany(mappedBy = "individualUser", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<BankAccount> bankAccounts;

    @OneToOne(mappedBy = "individualUser", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    BankCard bankCard;

    @OneToOne
    User user;

    @OneToMany(mappedBy = "individualUser")
    List<Lawsuits> lawsuits;

    @OneToMany(mappedBy = "individualUser")
    List<Payment> payments;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "last_modified_by")
    //@LastModifiedBy
    private Manager manager;
}
