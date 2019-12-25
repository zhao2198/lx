package com.lx.test.controller;


import com.lx.core.module.spring.response.ApiResponse;
import com.lx.ngt.vo.BookVO;
import com.lx.test.entity.Book;
import com.lx.test.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zhaowei
 * @since 2019-12-23
 */
@Controller
@RequestMapping("/test/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/insert")
    public ResponseEntity add() {

        Book book = new Book("庆余年", 20d, 1, "test");
        bookService.save(book);
        return new ApiResponse().ok();
    }


    @GetMapping("/{id}")
    public ResponseEntity<BookVO> get(@PathVariable("id") Long id) {
        BookVO bookVO = bookService.getBookById(id);
        return new ApiResponse().ok(bookVO);

    }


}
