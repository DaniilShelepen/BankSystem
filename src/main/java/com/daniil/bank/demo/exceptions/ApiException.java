package com.daniil.bank.demo.exceptions;


import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Data
@SuperBuilder
public class ApiException {

    private String message;
    private Throwable throwable;
    private HttpStatus httpStatus;
    private ZonedDateTime timeZone;
}
