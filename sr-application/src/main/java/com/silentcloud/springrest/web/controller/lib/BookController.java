package com.silentcloud.springrest.web.controller.lib;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.silentcloud.springrest.model.entity.lib.Book;
import com.silentcloud.springrest.service.api.dto.lib.BookDto;
import com.silentcloud.springrest.service.api.module.lib.BookService;
import com.silentcloud.springrest.web.controller.AbstractBaseController;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "图书管理")
@ApiSupport(order = 4)
@RequestMapping("/lib/books")
@RestController
public class BookController extends AbstractBaseController<Long, Book, BookDto> {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        super(bookService);
        this.bookService = bookService;
    }

}
