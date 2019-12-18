package com.lx.core.module.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(NGTJSONS.class)
public @interface NGTJSON {
    Class<?> type();
    String include() default "";
    String filter() default "";
}

