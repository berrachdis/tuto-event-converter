package com.tuto.commonlibrary.validation.validator;

import lombok.experimental.UtilityClass;

import javax.validation.ConstraintValidatorContext;

@UtilityClass
public class MessageValidatorUtils {

    public static void addConstraintViolation(ConstraintValidatorContext context, String messageTemplate, String propertyNode) {
        context.buildConstraintViolationWithTemplate(messageTemplate).addPropertyNode(propertyNode).addConstraintViolation();
    }
}
