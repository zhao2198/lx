package com.lx.framework.configure.redis;

import lombok.Data;

import java.util.Map;


@Data
public class LxCustomCacheProperties {

  private Map<String, LxCacheProperties> customCache;
}
