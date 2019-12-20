package com.lx.framework.configure.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.lx.framework.configure.BaseAutoConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.FatalBeanException;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.autoconfigure.cache.CacheProperties.Redis;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;


@Slf4j
@EnableCaching
@Configuration
@AutoConfigureAfter(RedisAutoConfiguration.class)
@EnableConfigurationProperties(CacheProperties.class)
@ConditionalOnClass({Redis.class, RedisCacheConfiguration.class})
public class RedisCacheAutoConfiguration extends BaseAutoConfiguration implements
    EnvironmentAware {

  private final CacheProperties cacheProperties;

  private ConfigurableEnvironment environment;

  public RedisCacheAutoConfiguration(CacheProperties cacheProperties) {
    this.cacheProperties = cacheProperties;
  }

//
//  @Bean // 关联redis到注解
//  CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
//
//    RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory);
//
//    // 默认配置，过期时间指定是30分钟
//    RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig();
//    defaultCacheConfig.entryTtl(Duration.ofMinutes(30));
//
//    // redisExpire1h cache配置，过期时间指定是1小时，缓存key的前缀指定成prefixaaa_（存到redis的key会自动添加这个前缀）
//    RedisCacheConfiguration userCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig().
//            entryTtl(Duration.ofHours(1)).prefixKeysWith("prefixaaa_");
//    Map<String, RedisCacheConfiguration> redisCacheConfigurationMap = new HashMap<>();
//    redisCacheConfigurationMap.put("redisExpire1h", userCacheConfiguration);
//
//    RedisCacheManager cacheManager = new RedisCacheManager(redisCacheWriter, defaultCacheConfig, redisCacheConfigurationMap);
//    return cacheManager;
//  }

  @Bean
  public RedisCacheManager getRedisCacheManager(RedisConnectionFactory redisConnectionFactory) {
    CustomRedisCacheWriter customRedisCacheWriter = new CustomRedisCacheWriter(
        RedisCacheWriter.lockingRedisCacheWriter(redisConnectionFactory));

    RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager.RedisCacheManagerBuilder
        .fromCacheWriter(customRedisCacheWriter).cacheDefaults(determineConfiguration());

    List<String> cacheNames = this.cacheProperties.getCacheNames();
    if (!cacheNames.isEmpty()) {
      try {
        LxCustomCacheProperties customCacheProperties = resolverSetting(
            LxCustomCacheProperties.class,
            this.environment.getPropertySources());
        Map<String, RedisCacheConfiguration> map = Maps.newHashMap();
        cacheNames.forEach(name -> {
          LxCacheProperties locCacheProperties = customCacheProperties.getCustomCache().get(name);
          RedisCacheConfiguration redisCacheConfiguration = determineConfiguration();
          if (locCacheProperties.getTimeToLive() != null) {
            redisCacheConfiguration = redisCacheConfiguration
                .entryTtl(locCacheProperties.getTimeToLive());
          }
          if (locCacheProperties.getKeyPrefix() != null) {
            redisCacheConfiguration = redisCacheConfiguration
                .prefixKeysWith(locCacheProperties.getKeyPrefix());
          }
          if (!locCacheProperties.isCacheNullValues()) {
            redisCacheConfiguration = redisCacheConfiguration.disableCachingNullValues();
          }
          if (!locCacheProperties.isUseKeyPrefix()) {
            redisCacheConfiguration = redisCacheConfiguration.disableKeyPrefix();
          }
          map.put(name, redisCacheConfiguration);
        });
        builder.withInitialCacheConfigurations(map);
      } catch (FatalBeanException e) {
        log.warn("may be not config customCache properties");
      }
    }
    return builder.build();
  }

  @Bean
  public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
    RedisTemplate<Object, Object> template = new RedisTemplate<>();
    template.setConnectionFactory(connectionFactory);

    // 使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值（默认使用JDK的序列化方式）
    Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(Object.class);

    ObjectMapper mapper = new ObjectMapper();
    mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
    mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
    mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"));
    serializer.setObjectMapper(mapper);

    template.setValueSerializer(serializer);
    // 使用StringRedisSerializer来序列化和反序列化redis的key值
    template.setKeySerializer(new StringRedisSerializer());
    template.afterPropertiesSet();
    return template;
  }

  @Bean
  public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory factory) {
    StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
    stringRedisTemplate.setConnectionFactory(factory);
    return stringRedisTemplate;
  }

  private RedisCacheConfiguration determineConfiguration() {
    Redis redisProperties = this.cacheProperties.getRedis();
    RedisCacheConfiguration config = RedisCacheConfiguration
        .defaultCacheConfig();

    Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(
        Object.class);

    ObjectMapper om = new ObjectMapper();
    om.setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.ANY);
    om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
    om.setSerializationInclusion(Include.NON_NULL);
    om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    jackson2JsonRedisSerializer.setObjectMapper(om);
    config = config
        .serializeKeysWith(SerializationPair.fromSerializer(new StringRedisSerializer()));
    config = config
        .serializeValuesWith(SerializationPair.fromSerializer(jackson2JsonRedisSerializer));

    if (redisProperties.getTimeToLive() != null) {
      config = config.entryTtl(redisProperties.getTimeToLive());
    }
    if (redisProperties.getKeyPrefix() != null) {
      config = config.prefixKeysWith(redisProperties.getKeyPrefix());
    }
    if (!redisProperties.isCacheNullValues()) {
      config = config.disableCachingNullValues();
    }
    if (!redisProperties.isUseKeyPrefix()) {
      config = config.disableKeyPrefix();
    }
    return config;
  }

  @Override
  public void setEnvironment(Environment environment) {
    this.environment = (ConfigurableEnvironment) environment;
  }
}
