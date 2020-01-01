package com.lx.core.module.feign;

import feign.Feign;
import feign.Retryer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@ConditionalOnClass({FeignClientsConfiguration.class, Feign.class})
@Configuration
@EnableConfigurationProperties(LxFeignRetryProperties.class)
@AutoConfigureBefore(FeignClientsConfiguration.class)
@Slf4j
public class LxFeignRetryAutoConfiguration {

    @Bean
    @ConditionalOnProperty(value = "lx.feign.retry.enabled", matchIfMissing = true)
    public Retryer feignRetryer(LxFeignRetryProperties locFeignRetryProperties) {
        return new Retryer.Default(locFeignRetryProperties.getPeriod(),
                TimeUnit.MILLISECONDS.toMillis(locFeignRetryProperties.getMaxPeriod()),
                locFeignRetryProperties.getMaxAttempts());
    }

}
