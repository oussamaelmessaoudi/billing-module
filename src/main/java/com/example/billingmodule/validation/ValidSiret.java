package com.example.billingmodule.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = SiretValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidSiret {
    String message() default "Siret must contains exactly 14 digits";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
