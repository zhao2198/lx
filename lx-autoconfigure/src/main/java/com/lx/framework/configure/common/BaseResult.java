package com.lx.framework.configure.common;

import lombok.Data;

@Data
public class BaseResult<T> {
  private int code;
  private String message;
  private String traceId;
  private T data;

  public BaseResult(T data) {
    this.code = 200;
    this.message = "success";
    this.data = data;
  }

  public BaseResult() {

    this.code = 200;
    this.message = "success";
  }

  public BaseResult(int code, String message) {
    this.code = code;
    this.message = message;
  }

  public static <T> BaseResult<T> success() {
    return new BaseResult<>();
  }

  public static <T> BaseResult<T> success(T t) {
    BaseResult<T> r = new BaseResult<>();
    r.setData(t);
    return r;
  }
}
