package com.w2m.exception;

import lombok.Getter;

@Getter
public class HeroeYaExistenteException extends RuntimeException{
    ResponseDefault responseDefault;

    public HeroeYaExistenteException(ResponseDefault response){
        super(response.getMensaje());
        this.responseDefault=response;

    }
}
