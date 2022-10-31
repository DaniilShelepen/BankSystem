package com.daniil.bank.demo.services;

import com.daniil.bank.demo.enums.ROLE;

public interface UserService {
    /**
     * Регистрация аккаунта
     **/
    void createAccount(String phoneNumber, String password, ROLE role);

}
