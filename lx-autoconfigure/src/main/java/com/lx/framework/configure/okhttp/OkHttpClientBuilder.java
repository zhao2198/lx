package com.lx.framework.configure.okhttp;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OkHttpClientBuilder {

  private String url;
  private Map<String, String> headers;
  private Map<String, String> params;
  private TypeReference typeReference;
  private Object tag;
  private String body;

}
