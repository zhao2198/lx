package com.lx.framework.configure.mybatis;

import lombok.Data;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Stream;


@Data
public class MybatisProperties {

  private static final ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();

  //datasource对应的mybatis-config.xml文件
  private String configLocation;

  private String[] mapperLocations;

  private String typeAliasesPackage;

  private String typeHandlersPackage;

  private String basePackage;

  private ExecutorType executorType;

  private Properties configurationProperties;

  @NestedConfigurationProperty
  private Configuration configuration;

  public Resource[] resolveMapperLocations() {
    return Stream.of(Optional.ofNullable(this.mapperLocations).orElse(new String[0]))
        .flatMap(location -> Stream.of(getResources(location)))
        .toArray(Resource[]::new);
  }

  private Resource[] getResources(String location) {
    try {
      return resourceResolver.getResources(location);
    } catch (IOException e) {
      return new Resource[0];
    }
  }
}
