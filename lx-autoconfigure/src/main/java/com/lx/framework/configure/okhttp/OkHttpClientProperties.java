package com.lx.framework.configure.okhttp;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;


@Data
@ConfigurationProperties(prefix = "lx.okhttp")
public class OkHttpClientProperties {

  private int connectTimeout = 5000;
  private int readTimeout = 30000;
  private int writeTimeout = 30000;
  private boolean retryOnConnectionFailure = true;
  private boolean followRedirects = true;
  private boolean followSslRedirects = true;
  private List<String> interceptors = new ArrayList<>();
  private List<String> networkInterceptors = new ArrayList<>();
  private Connection connection = new Connection();

  @Data
  public static class Connection {

    private int maxIdleConnections = 5;
    private long keepAliveDuration = 60_000;
  }
}
