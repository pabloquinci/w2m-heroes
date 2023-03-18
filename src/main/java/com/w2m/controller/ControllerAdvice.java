package com.w2m.controller;

import com.w2m.exception.HeroeNoValidoException;
import com.w2m.exception.ResponseDefault;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class ControllerAdvice {

    @ExceptionHandler(value= Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseDefault exceptionComun(Exception ex){
        return new ResponseDefault(ex.getMessage(), LocalDateTime.now());
    }


    @ExceptionHandler(value= HeroeNoValidoException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseDefault heroeNoEncontradoException(HeroeNoValidoException ex){
        return ex.getResponseDefault();
    }

    @ExceptionHandler(value= IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDefault illegalArgumentException(IllegalArgumentException ex){
        return new ResponseDefault(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDefault methodArgumentNotValidException(MethodArgumentNotValidException ex){
        return new ResponseDefault(ex.getMessage(), LocalDateTime.now());

    }


}
