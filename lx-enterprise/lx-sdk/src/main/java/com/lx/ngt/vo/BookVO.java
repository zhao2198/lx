package com.lx.ngt.vo;

import com.lx.common.transfer.TransferObject;
import com.lx.test.entity.Book;
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
public class BookVO extends TransferObject<Book> {

    private Long id;
    private String bookName;
    private Double price;
    private Integer bookType;
    private String author;


}
