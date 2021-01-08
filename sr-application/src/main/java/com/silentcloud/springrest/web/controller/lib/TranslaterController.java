package com.silentcloud.springrest.web.controller.lib;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.silentcloud.springrest.model.entity.lib.Translater;
import com.silentcloud.springrest.service.api.dto.lib.BookDto;
import com.silentcloud.springrest.service.api.dto.lib.TranslaterDto;
import com.silentcloud.springrest.service.api.module.lib.BookService;
import com.silentcloud.springrest.service.api.module.lib.TranslaterService;
import com.silentcloud.springrest.service.api.query.FlatQueryService;
import com.silentcloud.springrest.service.api.query.JpaQueryService;
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

@Api(tags = "译者管理")
@ApiSupport(order = 2)
@RequestMapping("/lib/translaters")
@RestController
public class TranslaterController extends AbstractBaseController<Long, Translater, TranslaterDto> {
    private final TranslaterService translaterService;
    private final BookService bookService;

    @Autowired
    public TranslaterController(JpaQueryService jpaQueryService,
                                FlatQueryService flatQueryService,
                                TranslaterService translaterService,
                                BookService bookService) {
        super(jpaQueryService, flatQueryService, translaterService);
        this.translaterService = translaterService;
        this.bookService = bookService;
    }

    @ApiOperationSupport(order = SUBCLASS_API_OPERATION_ORDER_OFFSET + 1)
    @RequiresPermViewDetail
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
