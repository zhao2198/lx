package com.lx.user.micro;


import com.lx.core.module.feign.FeignConfig;
import com.lx.core.module.spring.response.ApiResponse;
import com.lx.user.vo.UserVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "user", configuration = FeignConfig.class)
public interface UserClient {

    @RequestMapping(value = "/api/v1/user/{id}", method = RequestMethod.GET)
    ApiResponse<UserVO> getUser(@PathVariable("id") final Long id);
}
