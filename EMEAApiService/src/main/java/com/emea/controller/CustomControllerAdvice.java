package com.emea.controller;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.emea.exception.ErrorResponse;

@ControllerAdvice
public class CustomControllerAdvice {
    private static final Logger LOG = Logger
            .getLogger(CustomControllerAdvice.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {

        ErrorResponse error = new ErrorResponse();

        error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());

        error.setMessage("Please contact your administrator ");
        LOG.error(" Error occurred ", ex);

        return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);

    }

}
