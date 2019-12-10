package com.lx.framework.redis;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Service
public class RedisService {

  @Cacheable(value = "redis-demo-1", key = "'loc1'")
  public RedisDemoInfo getDemoInfoLocKey(String name, int age) {
    return getRedisDemoInfo(name, age);
  }

  private RedisDemoInfo getRedisDemoInfo(String name, int age) {
    UserDemoInfo userDemoInfo = UserDemoInfo.builder().name("dragon").date(new Date()).build();
    List<UserDemoInfo> userDemoInfos = Lists.newArrayList(userDemoInfo);
    return RedisDemoInfo.builder().name(name).age(age).userDemoInfoList(userDemoInfos).build();
  }


  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class RedisDemoInfo implements Serializable {

    private String name;
    private int age;

    private List<UserDemoInfo> userDemoInfoList;


  }

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  static class UserDemoInfo implements Serializable {

    private Date date;
    private String name;
  }

}
