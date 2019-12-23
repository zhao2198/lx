package com.lx.user.controller;


import com.lx.core.module.spring.annotation.ApiV1Controller;
import com.lx.core.module.spring.annotation.SnakeParam;
import com.lx.core.module.spring.response.ApiResponse;
import com.lx.user.dto.UserQueryDTO;
import com.lx.user.entity.User;
import com.lx.user.service.UserService;
import com.lx.user.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * <p>
 * 系统用户 前端控制器
 * </p>
 *
 * @author zhaowei
 * @since 2019-12-21
 */
@ApiV1Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/{id}")
    ResponseEntity getUser(@PathVariable("id") Long id) {
        UserVO userVO = userService.getUserById(id);
        return new ApiResponse().ok(userVO);
    }

    @GetMapping("/list")
    ResponseEntity list(@SnakeParam UserQueryDTO userQueryDTO) {
        User user = userQueryDTO.toObject(User.class);
        List<UserVO> voList = userService.getUserList(user);
        return new ApiResponse().ok(voList);
    }

}
