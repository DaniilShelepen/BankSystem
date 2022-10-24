package com.daniil.bank.demo.services.impl;

import com.daniil.bank.demo.dal.entity.legal.EntityUser;
import com.daniil.bank.demo.dal.entity.natural.IndividualUser;
import com.daniil.bank.demo.dal.entity.role.User;
import com.daniil.bank.demo.dal.repository.EntityRepository;
import com.daniil.bank.demo.dal.repository.IndividualRepository;
import com.daniil.bank.demo.dal.repository.UserRepository;
import com.daniil.bank.demo.enums.ROLE;
import com.daniil.bank.demo.security.PasswordEncoderConfig;
import com.daniil.bank.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final IndividualRepository individualRepository;

    private final EntityRepository entityRepository;

    private final UserRepository userRepository;

    private final PasswordEncoderConfig encoder;


    @Override
    public void createAccount(String phoneNumber, String password, ROLE role) {

        if (userRepository.findByPhoneNumber(phoneNumber) != null)
            throw new RuntimeException("User with this phone number already exist!"); // todo

        switch (role) {

            case INDIVIDUAL_ROLE -> {

                IndividualUser individualUser = Optional.ofNullable(
                                individualRepository.findByPhoneNumber(phoneNumber))
                        .orElseThrow(() -> new RuntimeException("Individual not found"));//todo


                User user = User.builder()
                        .phoneNumber(phoneNumber)
                        .password(encoder.passwordEncoder().encode(password))
                        .individualUser(individualUser)
                        .build();


                individualUser.setUser(user);

                userRepository.save(user);
                individualRepository.save(individualUser);


            }


            case ENTITY_ROLE -> {


                EntityUser entityUser = Optional.ofNullable(
                                entityRepository.findByPhoneNumber(phoneNumber))
                        .orElseThrow(() -> new RuntimeException("Entity not found"));//todo


                User user = User.builder()
                        .phoneNumber(phoneNumber)
                        .password(encoder.passwordEncoder().encode(password))
                        .entityUser(entityUser)
                        .build();


                entityUser.setUser(user);

                userRepository.save(user);
                entityRepository.save(entityUser);


            }


        }

    }
}
