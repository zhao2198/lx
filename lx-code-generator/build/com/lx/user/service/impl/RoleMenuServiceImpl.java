package com.lx.user.service.impl;

import com.lx.user.entity.RoleMenu;
import com.lx.user.mapper.RoleMenuMapper;
import com.lx.user.service.RoleMenuService;
import com.lx.core.module.service.mybatis.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色与菜单对应关系 服务实现类
 * </p>
 *
 * @author zhaowei
 * @since 2019-12-21
 */
@Service
public class RoleMenuServiceImpl extends BaseServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {

}
