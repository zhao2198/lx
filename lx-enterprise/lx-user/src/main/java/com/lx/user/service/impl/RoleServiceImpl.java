package com.lx.user.service.impl;

import com.lx.core.module.service.mybatis.impl.BaseServiceImpl;
import com.lx.user.entity.Role;
import com.lx.user.mapper.RoleMapper;
import com.lx.user.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author zhaowei
 * @since 2019-12-21
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleMapper, Role> implements RoleService {

}
