package com.lx.core.module.spring.config;


import com.lx.core.module.spring.dto.processer.SnakeKeyProcessor;
import com.lx.core.module.spring.format.ConditionStringToDateConverter;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

public class WebMvcConfig implements WebMvcConfigurer {

    public WebMvcConfig() {
    }

    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(new WebContextInterceptor()).addPathPatterns(new String[]{"/api/v1/**"});
        //registry.addInterceptor(new PermissionCheckInterceptor()).addPathPatterns(new String[]{"/api/v1/**"});
    }

    public void configurePathMatch(PathMatchConfigurer configurer) {
        //configurer.setUseSuffixPatternMatch(true).setUseTrailingSlashMatch(false).setUseRegisteredSuffixPatternMatch(true).addPathPrefix("/api/v1", HandlerTypePredicate.forAnnotation(new Class[]{ApiV1Controller.class})).addPathPrefix("/api/v2", HandlerTypePredicate.forAnnotation(new Class[]{ApiV2Controller.class}));
    }

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(new String[]{"/public/swagger.html"}).addResourceLocations(new String[]{"classpath:/META-INF/resources/"});
        registry.addResourceHandler(new String[]{"/webjars/**"}).addResourceLocations(new String[]{"classpath:/META-INF/resources/webjars/"});
    }

    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        //resolvers.add(new CurrentUserResolver());
        resolvers.add(this.snakeKeyProcessor());
    }

    @Bean
    public AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor() {
        return new AutowiredAnnotationBeanPostProcessor();
    }

    @Bean
    public SnakeKeyProcessor snakeKeyProcessor() {
        return new SnakeKeyProcessor(true);
    }

    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new ConditionStringToDateConverter());
    }
}
