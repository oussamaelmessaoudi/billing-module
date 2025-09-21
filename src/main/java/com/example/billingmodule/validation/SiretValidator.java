package com.example.billingmodule.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SiretValidator implements ConstraintValidator<ValidSiret, String> {
    @Override
    public boolean isValid(String siret, ConstraintValidatorContext context){
        if(siret == null)
            return false;
        return siret.matches("^\\d{14}$");
    }
}
