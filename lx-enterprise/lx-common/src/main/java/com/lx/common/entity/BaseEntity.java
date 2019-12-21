package com.lx.common.entity;


import lombok.Data;

import java.util.Date;

@Data
public class BaseEntity extends AbstractEntity {

    private Integer id;

    private Date createTime;

}
