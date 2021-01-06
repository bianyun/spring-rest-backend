package com.silentcloud.springrest.web.controller.lib;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.silentcloud.springrest.model.entity.lib.Translater;
import com.silentcloud.springrest.service.api.dto.lib.BookDto;
import com.silentcloud.springrest.service.api.dto.lib.TranslaterDto;
import com.silentcloud.springrest.service.api.module.lib.BookService;
import com.silentcloud.springrest.service.api.module.lib.TranslaterService;
import com.silentcloud.springrest.web.controller.AbstractBaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "译者管理")
@ApiSupport(order = 2)
@RequestMapping("/lib/translaters")
@RestController
public class TranslaterController extends AbstractBaseController<Long, Translater, TranslaterDto> {
    private final TranslaterService translaterService;
    private final BookService bookService;

    @Autowired
    public TranslaterController(TranslaterService translaterService,
                                BookService bookService) {
        super(translaterService);
        this.translaterService = translaterService;
        this.bookService = bookService;
    }

    @ApiOperation("通过ID获取译者翻译的所有图书")
    @GetMapping("/{id}/books")
    public List<BookDto> getBooksByTranslaterId(@PathVariable Long id) {
        if (translaterService.existsById(id)) {
            return bookService.getBooksByTranslaterId(id);
        } else {
            throw resourceNotFoundException(id);
        }
    }
}
