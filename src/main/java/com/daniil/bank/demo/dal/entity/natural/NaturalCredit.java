package com.daniil.bank.demo.dal.entity.natural;

import com.daniil.bank.demo.enums.CLIENT_STATUS;
import com.daniil.bank.demo.enums.CREDIT_STATUS;
import com.daniil.bank.demo.enums.CURRENCY;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class NaturalCredit {//еще раз проверь что все поля и что они В НУЖНОМ МЕСТЕ
    //и создай таблицы в которых будут доступные кредиты
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    private CREDIT_STATUS status;
    @Enumerated(EnumType.STRING)
    private CLIENT_STATUS clientStatus;
    private BigDecimal sum;

    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate loanTerm;//срок до какого должен быть выплачен кредит
    private Integer percentageRate;// процентная ставка
    @Enumerated(EnumType.STRING)
    private CURRENCY currency;//валюта
    private BigDecimal forfeit;//штраф
    @ManyToOne
    private Guarantor guarantor;
 //   private boolean fixedInterestRate;//фиксированная процентная ставка

    @ManyToOne
    private Individual individual;

}
