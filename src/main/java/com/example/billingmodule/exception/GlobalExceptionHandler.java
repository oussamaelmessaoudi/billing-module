package com.example.billingmodule.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<String> handleClientNotFound(ClientNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found !");
    }

    @ExceptionHandler(ClientAlreadyExistsException.class)
    public ResponseEntity<String> handleClientAlreadyExists(ClientAlreadyExistsException ex){
        if(ClientAlreadyExistsException.EXIST_TYPE.EXIST_BY_EMAIL.equals(ex.getType())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists !");
        }

        return ResponseEntity.status(HttpStatus.CONFLICT).body("Siret Already Exists !");
    }
}
