package com.lx.user.vo;

import com.lx.common.transfer.TransferObject;
import com.lx.user.entity.Menu;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 菜单管理
 * </p>
 *
 * @author zhaowei
 * @since 2019-12-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MenuVO extends TransferObject<Menu> {



    private Long menuId;

    /**
     * 父菜单ID，一级菜单为0
     */
    private Long parentId;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单URL
     */
    private String url;

    /**
     * 授权(多个用逗号分隔，如：user:list,user:create)
     */
    private String perms;

    /**
     * 类型   0：目录   1：菜单   2：按钮
     */
    private Integer type;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 排序
     */
    private Integer orderNum;


}
