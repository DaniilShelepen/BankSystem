package com.daniil.bank.demo.dal.repository;

import com.daniil.bank.demo.dal.entity.natural.Individual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Size;

public interface IndividualRepository extends JpaRepository<Individual, Long> {

    Individual findByPassportIDAndPassportSeries(@Size(min = 7, max = 7) String passportID, @Size(min = 2, max = 2) String passportSeries);

    @Transactional
    void deleteByPassportIDAndPassportSeries(@Size(min = 7, max = 7) String passportID, @Size(min = 2, max = 2) String passportSeries);
}
