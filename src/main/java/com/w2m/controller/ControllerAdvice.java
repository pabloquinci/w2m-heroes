package com.w2m.controller;

import com.w2m.exception.MandatoryParamsException;
import com.w2m.exception.ResponseDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(value= MandatoryParamsException.class)
    public ResponseDefault except(MandatoryParamsException ex){

        return ex.getResponseDefault();

    }

}
