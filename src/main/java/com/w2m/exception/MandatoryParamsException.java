package com.w2m.exception;

import lombok.Getter;

@Getter
public class MandatoryParamsException extends RuntimeException{
    ResponseDefault responseDefault;

    public MandatoryParamsException(ResponseDefault responseDefault){
        super(responseDefault.getMensaje());
        this.responseDefault=responseDefault;
    }

}
