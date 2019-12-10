package com.lx.framework.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zalando.problem.Problem;


@RestController
public class ServerController {

  @GetMapping("/server")
  public Problem server() {
    return Problem.builder().with("data", "hello world").build();
  }
}
