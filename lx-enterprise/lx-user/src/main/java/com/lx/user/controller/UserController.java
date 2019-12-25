package com.lx.user.controller;


import com.lx.core.module.spring.annotation.ApiV1Controller;
import com.lx.core.module.spring.annotation.SnakeParam;
import com.lx.core.module.spring.response.ApiResponse;
import com.lx.user.dto.UserQueryDTO;
import com.lx.user.entity.User;
import com.lx.user.service.UserService;
import com.lx.user.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

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
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private RedisLockRegistry redisLockRegistry;

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


    @GetMapping("/lock")
    ResponseEntity lock() throws InterruptedException {

        Lock lock = redisLockRegistry.obtain("lock");
        boolean b1 = lock.tryLock(10, TimeUnit.SECONDS);
        log.info("b1 is : {}", b1);

        TimeUnit.SECONDS.sleep(5);

        boolean b2 = lock.tryLock(3, TimeUnit.SECONDS);
        log.info("b2 is : {}", b2);

        lock.unlock();
        lock.unlock();
        return new ApiResponse().ok();
    }

    @GetMapping("/lock1")
    ResponseEntity lock1() throws InterruptedException {

        Lock lock = redisLockRegistry.obtain("lock");
        boolean b1 = lock.tryLock(3, TimeUnit.SECONDS);
        log.info("b1 is : {}", b1);

        TimeUnit.SECONDS.sleep(5);

        boolean b2 = lock.tryLock(3, TimeUnit.SECONDS);
        log.info("b2 is : {}", b2);

        lock.unlock();
        lock.unlock();
        return new ApiResponse().ok();
    }




}
