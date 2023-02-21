package com.w2m.exception;

import lombok.Getter;
import org.springframework.data.annotation.Transient;

@Getter
public class HeroeNoEncontradoException extends RuntimeException{
    ResponseDefault responseDefault;

    public HeroeNoEncontradoException(ResponseDefault responseDefault){
        super(responseDefault.getMensaje());
        this.responseDefault=responseDefault;
    }

}
