package com.lx.framework.configure.redis;


import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.integration.redis.util.RedisLockRegistry;

/**
 * 并发锁
 *
 * @Project RedisLockConfiguration(com.lx.framework.configure.redis)
 * @Author  zhaowei
 * @Date    2019/12/25 17:10
 * @Version v1.1.0
 */

@Configuration
@AutoConfigureAfter(RedisCacheAutoConfiguration.class)
@ConditionalOnClass({CacheProperties.Redis.class, RedisCacheConfiguration.class})
public class RedisLockConfiguration {

    @Bean
    public RedisLockRegistry redisLockRegistry(RedisConnectionFactory redisConnectionFactory) {
        return new RedisLockRegistry(redisConnectionFactory, "spring-cloud");
    }
}
