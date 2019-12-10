package com.lx.framework.configure;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;


@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@Conditional(PrefixPropertyCondition.class)
public @interface ConditionalOnPrefixProperty {

  String prefix() default "";

  Class<?> value();

}