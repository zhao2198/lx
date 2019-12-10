package com.lx.framework.configure.springmvc;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication.Type.SERVLET;


@Configuration
@RestControllerAdvice
@ConditionalOnWebApplication(type = SERVLET)
public class LocAdviceErrorAutoConfiguration implements LocServiceAdviceTrait {

}
