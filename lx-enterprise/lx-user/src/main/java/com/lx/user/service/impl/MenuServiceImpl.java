package com.lx.user.service.impl;

import com.lx.core.module.service.mybatis.impl.BaseServiceImpl;
import com.lx.user.entity.Menu;
import com.lx.user.mapper.MenuMapper;
import com.lx.user.service.MenuService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜单管理 服务实现类
 * </p>
 *
 * @author zhaowei
 * @since 2019-12-21
 */
@Service
public class MenuServiceImpl extends BaseServiceImpl<MenuMapper, Menu> implements MenuService {

}
