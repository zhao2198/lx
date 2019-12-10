package com.lx.framework.configure.mybatis;

import lombok.Data;

import java.util.Map;


@Data
public class LxMybatisProperties {

  private Map<String, MybatisProperties> dataSource;
}
