package com.silentcloud.springrest.web.controller.lib;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.silentcloud.springrest.model.entity.lib.Author;
import com.silentcloud.springrest.service.api.dto.lib.AuthorDto;
import com.silentcloud.springrest.service.api.dto.lib.BookDto;
import com.silentcloud.springrest.service.api.module.lib.AuthorService;
import com.silentcloud.springrest.service.api.module.lib.BookService;
import com.silentcloud.springrest.web.controller.AbstractBaseController;
import com.silentcloud.springrest.web.shiro.authz.annotation.RequiresPermViewDetail;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.silentcloud.springrest.web.util.Consts.SUBCLASS_API_OPERATION_ORDER_OFFSET;

@Api(tags = "作者管理")
@ApiSupport(order = 1)
@RequestMapping("/lib/authors")
@RestController
public class AuthorController extends AbstractBaseController<Long, Author, AuthorDto> {
    private final AuthorService authorService;
    private final BookService bookService;

    @Autowired
    public AuthorController(AuthorService authorService,
                            BookService bookService) {
        super(authorService);
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @ApiOperationSupport(order = SUBCLASS_API_OPERATION_ORDER_OFFSET + 1)
    @RequiresPermViewDetail
    @ApiOperation("通过ID获取作者的所有图书")
    @GetMapping("/{id}/books")
    public List<BookDto> getBooksByAuthorId(@PathVariable Long id) {
        if (authorService.existsById(id)) {
            return bookService.getBooksByAuthorId(id);
        } else {
            throw resourceNotFoundException(id);
        }
    }

}
