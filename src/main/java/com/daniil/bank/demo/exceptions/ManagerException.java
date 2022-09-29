package com.daniil.bank.demo.exceptions;

import lombok.experimental.SuperBuilder;


public class ManagerException extends RuntimeException {

    public ManagerException(String message) {
        super(message);
    }
}
