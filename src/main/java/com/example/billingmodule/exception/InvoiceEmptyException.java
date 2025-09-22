package com.example.billingmodule.exception;

public class InvoiceEmptyException extends RuntimeException{
    public InvoiceEmptyException(String message){
        super(message);
    }
}
