package com.lx.framework.configure.feign;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Data
@ConfigurationProperties(prefix = "loc-feign-retry")
public class LocFeignRetryProperties {

  private long period = 100;
  private long maxPeriod = 1000;
  private int maxAttempts = 0;

}
