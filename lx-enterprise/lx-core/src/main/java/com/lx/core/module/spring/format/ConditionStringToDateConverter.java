package com.lx.core.module.spring.format;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.DateUtil;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

@Component
public class ConditionStringToDateConverter implements ConditionalGenericConverter {
    public ConditionStringToDateConverter() {
    }

    public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
        return sourceType.getType().equals(String.class) && targetType.getType().equals(Date.class);
    }

    public Set<ConvertiblePair> getConvertibleTypes() {
        return Collections.singleton(new ConvertiblePair(String.class, Date.class));
    }

    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        if (source == null) {
            return null;
        } else {
            String value = String.valueOf(source);
            if (StringUtils.isEmpty(value)) {
                return null;
            } else {
                JsonFormat jsonFormat = (JsonFormat) targetType.getAnnotation(JsonFormat.class);
                if (jsonFormat != null) {
                    String pattern = jsonFormat.pattern();
                    if (StringUtils.isEmpty(pattern)) {
                        return DateUtil.parse(value);
                    } else {
                        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
                        Date parse = null;

                        try {
                            parse = dateFormat.parse(value);
                            return parse;
                        } catch (ParseException var10) {
                            var10.printStackTrace();
                            return null;
                        }
                    }
                } else {
                    return DateUtil.parse(value);
                }
            }
        }
    }
}
