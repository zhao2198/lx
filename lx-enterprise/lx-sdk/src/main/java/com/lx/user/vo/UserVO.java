package com.lx.user.vo;

import com.lx.common.transfer.TransferObject;
import com.lx.user.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 系统用户
 * </p>
 *
 * @author zhaowei
 * @since 2019-12-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserVO extends TransferObject<User> {

    private static final long serialVersionUID = 1L;

    private Long id;


    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 状态  0：禁用   1：正常
     */
    private Integer status;

    /**
     * 创建者ID
     */
    private Long createUserId;


}
