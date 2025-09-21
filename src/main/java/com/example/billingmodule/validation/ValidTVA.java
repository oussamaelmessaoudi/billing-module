package com.example.billingmodule.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Constraint(validatedBy = TVAValidator.class)
public @interface ValidTVA {
    String message() default "TVA must be one of: 0%, 5.5%, 10%, or 20%";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
