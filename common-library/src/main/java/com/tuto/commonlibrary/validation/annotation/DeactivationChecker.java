package com.tuto.commonlibrary.validation.annotation;

import com.tuto.commonlibrary.validation.validator.DeactivationValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = DeactivationValidator.class)
@Target(TYPE)
@Retention(RUNTIME)
public @interface DeactivationChecker {
    String message() default "DeactivationMessage is not valid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
