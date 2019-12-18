package com.lx.core.module.spring.validators.annotation;

import com.lx.core.module.spring.validators.DateTimeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(
        validatedBy = {DateTimeValidator.class}
)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DateTime {
    String value() default "yyyy-MM-dd HH:mm:ss";

    String message() default "Invalid datetime pattern. (yyyy-MM-dd HH:mm:ss)";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
