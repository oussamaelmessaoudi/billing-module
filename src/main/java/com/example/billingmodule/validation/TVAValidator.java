package com.example.billingmodule.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.math.BigDecimal;
import java.util.Set;

public class TVAValidator implements ConstraintValidator<ValidTVA, BigDecimal> {

    private static final Set<BigDecimal> VALID_TVA_VALUES = Set.of(
            BigDecimal.valueOf(0),
            BigDecimal.valueOf(5.5),
            BigDecimal.valueOf(10),
            BigDecimal.valueOf(20)

    );
    @Override
    public boolean isValid(BigDecimal tva, ConstraintValidatorContext context){
        if(tva == null) return false;

        return VALID_TVA_VALUES.contains(tva);
    }
}
