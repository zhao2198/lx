package com.lx.framework.configure.jdbc;

import lombok.Data;

import java.util.Map;


@Data
public class LxDsProperties {

  private Map<String, JdbcProperties> dataSource;
}
