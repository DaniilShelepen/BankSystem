package com.daniil.bank.demo.dal.repository;

import com.daniil.bank.demo.dal.entity.legal.EntityUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntityRepository extends JpaRepository<EntityUser, Long> {
    EntityUser findByName(String name);

    EntityUser findByPhoneNumber(String phoneNumber);

}
