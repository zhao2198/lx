package com.lx.user.service.impl;

import com.lx.user.entity.User;
import com.lx.user.mapper.UserMapper;
import com.lx.user.service.UserService;
import com.lx.core.module.service.mybatis.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户 服务实现类
 * </p>
 *
 * @author zhaowei
 * @since 2019-12-21
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements UserService {

}
