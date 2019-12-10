package com.lx.framework.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.zalando.problem.Problem;



@FeignClient(name = "cloud-server-sample", fallbackFactory = com.lx.framework.feign.FeignClientSampleFallBackFactory.class)
public interface FeignClientSample {

  @RequestMapping(value = "/server", method = RequestMethod.GET)
  Problem server();
}
