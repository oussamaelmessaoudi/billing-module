package com.example.billingmodule.exception;

import lombok.Getter;

@Getter
public class ClientAlreadyExistsException extends RuntimeException {
    public enum EXIST_TYPE {
        EXIST_BY_SIRET,
        EXIST_BY_EMAIL
    }

    private final EXIST_TYPE type;

    public ClientAlreadyExistsException(String message,EXIST_TYPE type){
        super(message);
        this.type=type;
    }

}
