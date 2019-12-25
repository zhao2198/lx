package com.lx.test.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lx.common.entity.BaseEntity;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_book")
public class Book extends BaseEntity {

    private String bookName;
    private Double price;
    private Integer bookType;
    private String author;
}
