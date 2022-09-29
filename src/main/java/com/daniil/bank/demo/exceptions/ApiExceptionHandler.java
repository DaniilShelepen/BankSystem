package com.daniil.bank.demo.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {


    @ExceptionHandler({IndividualException.class})
    public ResponseEntity<Object> handlerIndividualException(IndividualException exception) {
        HttpStatus httpStatus = HttpStatus.SERVICE_UNAVAILABLE;

        ApiException apiException = ApiException.builder()
                .message(exception.getMessage())
                .httpStatus(httpStatus)
                .throwable(exception)
                .timeZone(ZonedDateTime.now())
                .build();
        return new ResponseEntity<>(apiException, httpStatus);
    }



    @ExceptionHandler({ManagerException.class})
    public ResponseEntity<Object> handlerManagerException(ManagerException exception) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        ApiException apiException = ApiException.builder()
                .message(exception.getMessage())
                .httpStatus(httpStatus)
                .throwable(exception)
                .timeZone(ZonedDateTime.now())
                .build();
        return new ResponseEntity<>(apiException, httpStatus);
    }

}
