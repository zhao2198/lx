package com.lx.core.module.spring.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContextUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringContextUtil.class);

    public SpringContextUtil() {
    }

    public void setApplicationContext(ApplicationContext ac) throws BeansException {
        applicationContext = ac;
        LOGGER.info("init SpringContextUtil.");
    }

    public static Object getBean(final String beanName) {
        return applicationContext.getBean(beanName);
    }

    public static <T> T getBean(final Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }
}
