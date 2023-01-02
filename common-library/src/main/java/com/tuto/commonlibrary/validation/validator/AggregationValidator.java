package com.tuto.commonlibrary.validation.validator;

import com.tuto.commonlibrary.model.message.AggregationMessage;
import com.tuto.commonlibrary.validation.annotation.AggregationChecker;
import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static com.tuto.commonlibrary.validation.validator.MessageValidatorUtils.addConstraintViolation;

public class AggregationValidator implements ConstraintValidator<AggregationChecker, AggregationMessage> {

    @Override
    public boolean isValid(AggregationMessage msg, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        var isValid = true;

        if ((msg.getAggregationType() == 1 || msg.getAggregationType() == 3) && CollectionUtils.isEmpty(msg.getAggregatedUis1())) {
            addConstraintViolation(context, "AggregatedUIs1 is mandatory and must not be null or empty because aggrationType is equals to 1 or 3", "AggregatedUIs1");
            isValid = false;
        }

        if ((msg.getAggregationType() == 2 || msg.getAggregationType() == 3) && CollectionUtils.isEmpty(msg.getAggregatedUis2())) {
            addConstraintViolation(context, "AggregatedUIs2 is mandatory and must not be null or empty because aggrationType is equals to 2 or 3", "AggregatedUIs2");
            isValid = false;
        }

        return isValid;
    }
}
