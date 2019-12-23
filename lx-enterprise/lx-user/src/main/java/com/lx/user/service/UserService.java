package com.lx.user.service;

import com.lx.core.module.service.mybatis.BaseService;
import com.lx.user.entity.User;
import com.lx.user.vo.UserVO;

import java.util.List;

/**
 * <p>
 * 系统用户 服务类
 * </p>
 *
 * @author zhaowei
 * @since 2019-12-21
 */
public interface UserService extends BaseService<User> {


    /**
     * 根据用户ID查询用户
     * @param id
     * @return
     */
    UserVO getUserById(Long id);


    List<UserVO> getUserList(User user);


}
