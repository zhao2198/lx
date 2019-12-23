package com.lx.test.service;

import com.lx.ngt.vo.BookVO;
import com.lx.test.entity.Book;
import com.lx.core.module.service.mybatis.BaseService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhaowei
 * @since 2019-12-23
 */
public interface BookService extends BaseService<Book> {

    public BookVO getBookById(Long id);

}
