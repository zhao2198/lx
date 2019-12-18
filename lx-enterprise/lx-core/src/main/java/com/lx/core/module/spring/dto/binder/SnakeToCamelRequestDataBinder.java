package com.lx.core.module.spring.dto.binder;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.web.servlet.mvc.method.annotation.ExtendedServletRequestDataBinder;

import javax.servlet.ServletRequest;
import java.util.Map;


public class SnakeToCamelRequestDataBinder extends ExtendedServletRequestDataBinder {
    private final Map<String, String> paramMappings;

    public SnakeToCamelRequestDataBinder(Object target, String objectName, Map<String, String> map) {
        super(target, objectName);
        this.paramMappings = map;
    }

    protected void addBindValues(MutablePropertyValues values, ServletRequest request) {
        super.addBindValues(values, request);
        this.paramMappings.forEach((snake, camel) -> {
            if (values.contains(snake)) {
                values.add(camel, values.getPropertyValue(snake).getValue());
            }

        });
    }
}
