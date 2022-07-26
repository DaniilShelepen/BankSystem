package com.daniil.bank.demo.dal.entity.legal;

import com.daniil.bank.demo.finance.CLIENT_STATUS;
import com.daniil.bank.demo.finance.CURRENCY;
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
    private Integer id;


    private BigDecimal credit;
    private String description;
    private Long timeMonth;
    private Integer percentageRate;// процентная ставка
    @Enumerated(EnumType.STRING)
    private CURRENCY currency;

    @Enumerated(EnumType.STRING)
    private CLIENT_STATUS client_status;

    private boolean available;
}
