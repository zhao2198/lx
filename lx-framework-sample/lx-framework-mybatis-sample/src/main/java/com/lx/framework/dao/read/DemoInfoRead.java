package com.lx.framework.dao.read;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lx.framework.domain.DemoInfo;
import org.apache.ibatis.annotations.Param;

public interface DemoInfoRead extends BaseMapper<DemoInfo> {

  IPage<DemoInfo> getAllDemoInfo(Page page);

  IPage<DemoInfo> getInfoByScore(Page page, @Param("score") Integer score);

  IPage<DemoInfo> getInfoByBeanPage(Page page, @Param(value="info") DemoInfo demoInfo);
}
