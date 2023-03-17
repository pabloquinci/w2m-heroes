package com.w2m.controller;

import com.w2m.exception.HeroeNoEncontradoException;
import com.w2m.exception.HeroeYaExistenteException;
import com.w2m.exception.ResponseDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(value= Exception.class)
    public ResponseEntity<String> exceptionComun(Exception ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
    }


    @ExceptionHandler(value= HeroeNoEncontradoException.class)
    public ResponseEntity<ResponseDefault> heroeNoEncontradoException(HeroeNoEncontradoException ex){
        return new ResponseEntity<ResponseDefault>(ex.getResponseDefault(), HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(value= HeroeYaExistenteException.class)
    public ResponseEntity<ResponseDefault> heroeExistenteException(HeroeYaExistenteException ex){
        return new ResponseEntity<ResponseDefault>(ex.getResponseDefault(), HttpStatus.FOUND);
    }

    @ExceptionHandler(value= IllegalArgumentException.class)
    public ResponseEntity<String> illegalArgumentException(IllegalArgumentException ex){
        return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<String> methodArgumentNotValidException(MethodArgumentNotValidException ex){
        return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);

    }


}
