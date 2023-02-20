package com.w2m.exception;

import lombok.Getter;

@Getter
public class HeroeNoEncontradoException extends RuntimeException{
    ResponseDefault responseDefault;

    public HeroeNoEncontradoException(ResponseDefault responseDefault){
        super(responseDefault.getMessage());
        this.responseDefault=responseDefault;
    }

}
