package com.lx.core.module.feign;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Data
@ConfigurationProperties(prefix = "lx.feign.retry")
public class LxFeignRetryProperties {

  private long period = 100;
  private long maxPeriod = 1000;
  private int maxAttempts = 0;

}
