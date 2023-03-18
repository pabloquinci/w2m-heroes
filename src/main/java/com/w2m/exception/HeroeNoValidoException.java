package com.w2m.exception;

import lombok.Getter;

@Getter
public class HeroeNoValidoException extends RuntimeException{
    ResponseDefault responseDefault;

    public HeroeNoValidoException(ResponseDefault responseDefault){
        super(responseDefault.getMensaje());
        this.responseDefault=responseDefault;
    }

}
