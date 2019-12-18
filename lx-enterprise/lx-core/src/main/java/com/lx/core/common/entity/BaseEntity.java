package com.lx.core.common.entity;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseEntity implements Serializable {

    private Long id;

    private Date createTime;

}
