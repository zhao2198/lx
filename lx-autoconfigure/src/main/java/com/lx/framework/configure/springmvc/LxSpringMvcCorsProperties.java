package com.lx.framework.configure.springmvc;

import com.google.common.collect.Lists;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;



@Data
@ConfigurationProperties(prefix = "lx.web.springmvc.cors")
public class LxSpringMvcCorsProperties {

  private String path;

  private List<String> allowOrigins = Lists.newArrayList("*");
  private List<String> allowHeaders = Lists.newArrayList("*");
  private List<String> allowMethods = Lists.newArrayList("*");
  private List<String> allowExposeHeaders;
}
