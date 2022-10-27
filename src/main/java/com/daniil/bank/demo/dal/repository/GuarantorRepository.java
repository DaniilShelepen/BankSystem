package com.daniil.bank.demo.dal.repository;

import com.daniil.bank.demo.dal.entity.Guarantor;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.Size;

public interface GuarantorRepository extends JpaRepository<Guarantor, Long> {

    Guarantor findByPassportIDAndPassportSeries(@Size(min = 7, max = 7) String passportID, @Size(min = 2, max = 2) String passportSeries);

}
