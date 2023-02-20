package com.w2m.controller;

import com.w2m.exception.HeroeNoEncontradoException;
import com.w2m.exception.MandatoryParamsException;
import com.w2m.exception.ResponseDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(value= Exception.class)
    public ResponseEntity<String> exceptionComun(Exception ex){

        return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);

    }

    @ExceptionHandler(value= MandatoryParamsException.class)
    public ResponseEntity<ResponseDefault> mandatoryParamsException(MandatoryParamsException ex){

        return new ResponseEntity<>(ex.getResponseDefault(), HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(value= HeroeNoEncontradoException.class)
    public ResponseEntity<ResponseDefault> except(HeroeNoEncontradoException ex){

        return new ResponseEntity<ResponseDefault>(ex.getResponseDefault(), HttpStatus.NO_CONTENT);

    }

}
