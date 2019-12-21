package com.lx.user.service.impl;

import com.lx.core.module.service.mybatis.impl.BaseServiceImpl;
import com.lx.user.entity.UserRole;
import com.lx.user.mapper.UserRoleMapper;
import com.lx.user.service.UserRoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户与角色对应关系 服务实现类
 * </p>
 *
 * @author zhaowei
 * @since 2019-12-21
 */
@Service
public class UserRoleServiceImpl extends BaseServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}
