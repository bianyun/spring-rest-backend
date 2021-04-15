package com.silentcloud.springrest.web.controller.lib;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.silentcloud.springrest.model.entity.lib.Publisher;
import com.silentcloud.springrest.service.api.dto.lib.BookDto;
import com.silentcloud.springrest.service.api.dto.lib.PublisherDto;
import com.silentcloud.springrest.service.api.module.lib.BookService;
import com.silentcloud.springrest.service.api.module.lib.PublisherService;
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

/**
 * 出版社管理
 *
 * @author bianyun
 */
@Api(tags = "出版社管理")
@ApiSupport(order = 3)
@RequestMapping("/lib/publishers")
@RestController
public class PublisherController extends AbstractBaseController<Long, Publisher, PublisherDto> {
    private final PublisherService publisherService;
    private final BookService bookService;

    @Autowired
    public PublisherController(JpaQueryService jpaQueryService,
                               FlatQueryService flatQueryService,
                               PublisherService publisherService,
                               BookService bookService) {
        super(jpaQueryService, flatQueryService, publisherService);
        this.publisherService = publisherService;
        this.bookService = bookService;
    }

    @ApiOperationSupport(order = SUBCLASS_API_OPERATION_ORDER_OFFSET + 2)
    @RequiresPermViewDetail
    @ApiOperation("通过ID获取该出版社的所有图书")
    @GetMapping("/{id}/books")
    public List<BookDto> getBooksByPublisherId(@PathVariable Long id) {
        if (publisherService.existsById(id)) {
            return bookService.getBooksByPublisherId(id);
        } else {
            throw resourceNotFoundException(id);
        }
    }
}
