package com.daniil.bank.demo.dal.repository;

import com.daniil.bank.demo.dal.entity.role.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByPhoneNumber(String phoneNumber);


}
