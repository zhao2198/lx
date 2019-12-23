package com.lx.user.service.impl;

import com.google.common.collect.Lists;
import com.lx.core.module.service.mybatis.impl.BaseServiceImpl;
import com.lx.user.mapper.UserMapper;
import com.lx.user.service.UserService;
import com.lx.user.entity.User;
import com.lx.user.vo.UserVO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    @Resource
    private UserMapper userMapper;

    @Override
    public UserVO getUserById(Long id) {
        User user = userMapper.selectById(id);
        if(null != user) {
            UserVO userVO = new UserVO();
            userVO.convert(user);
            return userVO;
        }
        return null;
    }

    @Override
    public List<UserVO> getUserList(User user) {
        List<User> list = userMapper.queryList(user);
        List<UserVO> voList = Lists.newArrayList();
        if(CollectionUtils.isNotEmpty(list)) {

            list.forEach(u -> {
                UserVO userVO = new UserVO();
                userVO.convert(u);
                voList.add(userVO);
            });
        }
        return voList;
    }
}
