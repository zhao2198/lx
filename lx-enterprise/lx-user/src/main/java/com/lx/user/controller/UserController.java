package com.lx.user.controller;


import com.lx.core.module.spring.response.ApiResponse;
import com.lx.user.service.UserService;
import com.lx.user.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 系统用户 前端控制器
 * </p>
 *
 * @author zhaowei
 * @since 2019-12-21
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/{id}")
    ResponseEntity getUser(@PathVariable("id") Long id) {
        UserVO userVO = userService.getUserById(id);
        return new ApiResponse().ok(userVO);
    }

}
