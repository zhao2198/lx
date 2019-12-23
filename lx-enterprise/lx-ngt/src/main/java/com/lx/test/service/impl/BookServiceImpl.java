package com.lx.test.service.impl;

import com.lx.core.module.service.mybatis.impl.BaseServiceImpl;
import com.lx.ngt.vo.BookVO;
import com.lx.test.entity.Book;
import com.lx.test.mapper.BookMapper;
import com.lx.test.service.BookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhaowei
 * @since 2019-12-23
 */
@Service
public class BookServiceImpl extends BaseServiceImpl<BookMapper, Book> implements BookService {

    @Resource
    private BookMapper bookMapper;

    @Override
    public BookVO getBookById(Long id) {
        Book book = bookMapper.selectById(id);
        BookVO bookVO = null;
        if (null != book) {
            bookVO = new BookVO();
            bookVO.convert(book);
        }
        return bookVO;
    }
}
