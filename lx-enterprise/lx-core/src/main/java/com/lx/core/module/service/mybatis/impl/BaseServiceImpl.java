package com.lx.core.module.service.mybatis.impl;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

public class BaseServiceImpl<M extends BaseMapper<E>, E> extends ServiceImpl<BaseMapper<E>, E> {

}
