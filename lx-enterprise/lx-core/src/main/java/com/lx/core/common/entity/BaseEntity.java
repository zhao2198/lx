package com.lx.core.common.entity;


import lombok.Data;

import java.util.Date;

@Data
public class BaseEntity extends AbstractEntity {

    private Long id;

    private Date createTime;


    @Override
    public void setId(Object id) {
        setId(id==null?null:String.valueOf(id));
    }
}
