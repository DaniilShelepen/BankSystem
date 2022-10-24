package com.daniil.bank.demo.dal.entity.legal;

import com.daniil.bank.demo.dal.entity.role.Manager;
import com.daniil.bank.demo.enums.CLIENT_STATUS;
import com.daniil.bank.demo.enums.CURRENCY;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class LegalOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    private BigDecimal sum;
    private String description;
    private Long timeMonth;
    private Integer percentageRate;// процентная ставка
    @Enumerated(EnumType.STRING)
    private CURRENCY currency;

    @Enumerated(EnumType.STRING)
    private CLIENT_STATUS clientStatus;

    private boolean available;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "last_modified_by")
    //@LastModifiedBy
    private Manager manager;
}
