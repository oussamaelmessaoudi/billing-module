package com.example.billingmodule.exception;

public class InvoiceNotFoundException extends RuntimeException{
    public InvoiceNotFoundException(String message){
        super(message);
    }
}
