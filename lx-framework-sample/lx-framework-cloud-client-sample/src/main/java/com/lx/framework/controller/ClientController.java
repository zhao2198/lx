package com.lx.framework.controller;

import com.lx.framework.feign.FeignClientSample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zalando.problem.Problem;


@RestController
public class ClientController {

  @Autowired
  private FeignClientSample feignClientSample;

  @GetMapping("/client")
  public Problem client() {
    return feignClientSample.server();
  }
}
