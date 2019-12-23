package com.lx.user.vo;

import com.lx.common.transfer.TransferObject;
import com.lx.user.entity.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 角色
 * </p>
 *
 * @author zhaowei
 * @since 2019-12-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleVO extends TransferObject<Role> {

    private static final long serialVersionUID = 1L;

    private Long roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建者ID
     */
    private Long createUserId;


}
