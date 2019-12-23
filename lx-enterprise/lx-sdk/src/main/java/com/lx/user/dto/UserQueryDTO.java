package com.lx.user.dto;

import com.lx.common.transfer.TransferObject;
import com.lx.user.entity.User;
import lombok.Data;


@Data
public class UserQueryDTO extends TransferObject<User> {

    private String userName;
    private String status;
}
