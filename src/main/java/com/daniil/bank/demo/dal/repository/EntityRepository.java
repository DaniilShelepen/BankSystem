package com.daniil.bank.demo.dal.repository;

import com.daniil.bank.demo.dal.entity.legal.Entity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntityRepository extends JpaRepository<Entity, Long> {
    Entity findByName(String name);
}
