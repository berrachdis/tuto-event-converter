package com.tuto.commonlibrary.validation.validator;

import com.tuto.commonlibrary.model.message.DeactivationMessage;
import com.tuto.commonlibrary.validation.annotation.DeactivationChecker;
import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static com.tuto.commonlibrary.validation.validator.MessageValidatorUtils.addConstraintViolation;

public class DeactivationValidator implements ConstraintValidator<DeactivationChecker, DeactivationMessage> {

    @Override
    public boolean isValid(DeactivationMessage msg, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        var isValid = true;

        if ((msg.getDeactType() == 1 || msg.getDeactType() == 3) && CollectionUtils.isEmpty(msg.getDeactUpUIs())) {
            addConstraintViolation(context, "DeactUpUIs is mandatory and must not be null or empty because deactType is equals to 1 or 3", "DeactUpUIs");
            isValid = false;
        }

        if ((msg.getDeactType() == 2 || msg.getDeactType() == 3) && CollectionUtils.isEmpty(msg.getDeactAUIs())) {
            addConstraintViolation(context, "DeactAUIs is mandatory and must not be null or empty because deactType is equals to 2 or 3", "DeactAUIs");
            isValid = false;
        }

        return isValid;
    }
}
