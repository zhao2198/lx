package com.lx.common.entity;


import lombok.Data;

import java.util.Date;

@Data
public class BaseEntity extends AbstractEntity {

    private Long id;

    private Date createTime;

}
