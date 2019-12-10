package com.lx.framework.configure.redis;

import lombok.Data;

import java.time.Duration;


@Data
public class LxCacheProperties {

  private Duration timeToLive;
  private boolean cacheNullValues = true;
  private String keyPrefix;
  private boolean useKeyPrefix = true;
}
