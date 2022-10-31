package com.daniil.bank.demo.dal.repository;

import com.daniil.bank.demo.dal.entity.natural.NaturalCredit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.time.LocalDate;

public interface NaturalCreditRepository extends JpaRepository<NaturalCredit, Long> {
    NaturalCredit findByNumber(String number);


    @Query(value = "select sum(natural_credit.sum) month_sum\n" +
            "from public.natural_credit\n" +
            "where registration_date BETWEEN :localDate and\n" +
            "(date_trunc('month', date :localDate) + interval '1 month - 1 day')\n" +
            "", nativeQuery = true)
    String banksTotalWithdrawal(@Param("localDate") LocalDate localDate);//@Param("value") String value

}
