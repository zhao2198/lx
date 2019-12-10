package com.lx.framework.configure.springmvc;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication.Type;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.zalando.problem.ProblemModule;
import org.zalando.problem.violations.ConstraintViolationProblemModule;

import javax.servlet.Filter;
import java.util.Optional;



@Configuration
@ConditionalOnWebApplication(type = Type.SERVLET)
@EnableConfigurationProperties(LxSpringMvcCorsProperties.class)
public class LxSpringMvcAutoConfiguration {

  @Bean
  @ConditionalOnClass(CorsFilter.class)
  @ConditionalOnProperty(value = "lx.web.springmvc.cors.enabled")
  public Filter corsFilter(LxSpringMvcCorsProperties locSpringMvcCorsProperties) {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration(
        Optional.ofNullable(locSpringMvcCorsProperties.getPath()).orElse("/**"),
        buildConfig(locSpringMvcCorsProperties));
    return new CorsFilter(source);
  }

  private CorsConfiguration buildConfig(LxSpringMvcCorsProperties locSpringMvcCorsProperties) {
    CorsConfiguration corsConfiguration = new CorsConfiguration();
    Optional.ofNullable(locSpringMvcCorsProperties.getAllowOrigins())
        .ifPresent(origins -> origins.forEach(corsConfiguration::addAllowedOrigin));
    Optional.ofNullable(locSpringMvcCorsProperties.getAllowHeaders())
        .ifPresent(headers -> headers.forEach(corsConfiguration::addAllowedHeader));
    Optional.ofNullable(locSpringMvcCorsProperties.getAllowMethods())
        .ifPresent(methods -> methods.forEach(corsConfiguration::addAllowedMethod));
    Optional.ofNullable(locSpringMvcCorsProperties.getAllowExposeHeaders())
        .ifPresent(headers -> headers.forEach(corsConfiguration::addExposedHeader));
    return corsConfiguration;
  }

  @Bean
  public ProblemModule problemModule() {
    return new ProblemModule();
  }

  @Bean
  public ConstraintViolationProblemModule constraintViolationProblemModule() {
    return new ConstraintViolationProblemModule();
  }
}
