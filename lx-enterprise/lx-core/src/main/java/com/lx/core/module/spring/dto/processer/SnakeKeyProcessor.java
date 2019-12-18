package com.lx.core.module.spring.dto.processer;


import com.lx.common.util.StringUtils;
import com.lx.core.module.spring.annotation.SnakeParam;
import com.lx.core.module.spring.dto.binder.SnakeToCamelRequestDataBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.ServletModelAttributeMethodProcessor;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class SnakeKeyProcessor extends ServletModelAttributeMethodProcessor {
    private static Map<Class<?>, Map<String, String>> CACHED_MAPPING_CLASS = new HashMap<>();
    @Autowired
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    public SnakeKeyProcessor(boolean annotationNotRequired) {
        super(annotationNotRequired);
    }

    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(SnakeParam.class);
    }

    protected void bindRequestParameters(WebDataBinder binder, NativeWebRequest request) {
        Object target = binder.getTarget();
        Class<?> clazz = target.getClass();
        String objectName = binder.getObjectName();
        SnakeToCamelRequestDataBinder snake = new SnakeToCamelRequestDataBinder(target, objectName, this.getSnakeMapping(clazz));
        this.requestMappingHandlerAdapter.getWebBindingInitializer().initBinder(snake);
        super.bindRequestParameters(snake, request);
    }

    private Map<String, String> getSnakeMapping(Class<?> clazz) {
        if (clazz == null) {
            return null;
        } else {
            if (!CACHED_MAPPING_CLASS.containsKey(clazz)) {
                Map<String, String> map = new HashMap<>();
                Field[] fields = clazz.getDeclaredFields();
                Field[] var4 = fields;
                int var5 = fields.length;

                for(int var6 = 0; var6 < var5; ++var6) {
                    Field field = var4[var6];
                    String fieldName = field.getName();
                    String snakeName = StringUtils.camelToUnderline(fieldName);
                    map.put(snakeName, fieldName);
                }

                CACHED_MAPPING_CLASS.put(clazz, map);
            }

            return (Map)CACHED_MAPPING_CLASS.get(clazz);
        }
    }
}
