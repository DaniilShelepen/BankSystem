package com.daniil.bank.demo.security;

import com.daniil.bank.demo.dal.entity.role.User;
import com.daniil.bank.demo.dal.repository.IndividualRepository;
import com.daniil.bank.demo.enums.ROLE;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
@Configuration
public class IndividualUserDetailsServiceImpl implements UserDetailsService {

    private final IndividualRepository individualRepository;


    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {


        User user = individualRepository.findByPhoneNumber(phoneNumber).getUser();


        return  org.springframework.security.core.userdetails.User.builder()
                .username(user.getPhoneNumber())
                .password(user.getPassword())
                .roles(ROLE.INDIVIDUAL_ROLE.toString())
                .build();
    }
}
