package com.daniil.bank.demo.dal.repository;

import com.daniil.bank.demo.dal.entity.natural.IndividualUser;
import com.daniil.bank.demo.dto.IndividualsAndCardsDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Size;
import java.util.List;

public interface IndividualRepository extends JpaRepository<IndividualUser, Long> {

    IndividualUser findByPassportIDAndPassportSeries(@Size(min = 7, max = 7) String passportID, @Size(min = 2, max = 2) String passportSeries);

    IndividualUser findByPhoneNumber(String phoneNumber);

    @Transactional
    void deleteByPassportIDAndPassportSeries(@Size(min = 7, max = 7) String passportID, @Size(min = 2, max = 2) String passportSeries);

    @Query(nativeQuery = true)
    List<IndividualsAndCardsDto> getIndividualsAndCards();

}
