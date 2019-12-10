package com.lx.framework.configure;

import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException {

  private String msg;

  private int code = -1;


  public ServiceException(String msg) {
    super(msg);
    this.msg = msg;
  }

  public ServiceException(String msg, Throwable e) {
    super(msg, e);
    this.msg = msg;
  }

  public ServiceException(int code, String msg) {
    super(msg);
    this.msg = msg;
    this.code = code;
  }

  public ServiceException(int code, String msg, Throwable e) {
    super(msg, e);
    this.msg = msg;
    this.code = code;
  }

}
