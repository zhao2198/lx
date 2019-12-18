package com.lx.core.module.spring.validators;


import com.lx.core.module.spring.validators.annotation.DateTime;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateTimeValidator implements ConstraintValidator<DateTime, String> {
    private static final Logger LOGGER = LoggerFactory.getLogger(DateTimeValidator.class);
    private DateTime dateTime;

    public DateTimeValidator() {
    }

    public void initialize(DateTime constraintAnnotation) {
        this.dateTime = constraintAnnotation;
    }

    public boolean isValid(String date, ConstraintValidatorContext constraintValidatorContext) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(this.dateTime.value());
        }

        String pattern = this.dateTime.value();
        if (StringUtils.isEmpty(date)) {
            return false;
        } else {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

            try {
                simpleDateFormat.parse(date);
                return true;
            } catch (ParseException var6) {
                return false;
            }
        }
    }
}
