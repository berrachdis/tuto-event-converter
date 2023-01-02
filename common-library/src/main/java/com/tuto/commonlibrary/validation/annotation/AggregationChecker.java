package com.tuto.commonlibrary.validation.annotation;

import com.tuto.commonlibrary.validation.validator.AggregationValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = AggregationValidator.class)
@Target(TYPE)
@Retention(RUNTIME)
public @interface AggregationChecker {
    String message() default "AggregationMessage is not valid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
