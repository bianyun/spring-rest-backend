package com.silentcloud.springrest.service.api.module.lib;

import com.silentcloud.springrest.model.entity.lib.Book;
import com.silentcloud.springrest.service.api.dto.lib.BookDto;
import com.silentcloud.springrest.service.api.module.BaseService;

import java.util.List;

/**
 * 图书服务接口
 *
 * @author bianyun
 */
public interface BookService extends BaseService<Long, Book, BookDto> {

    /**
     * 根据 出版社ID 获取相关的图书列表
     *
     * @param publisherId 出版社ID
     * @return 相关的图书列表
     */
    List<BookDto> getBooksByPublisherId(Long publisherId);

    /**
     * 根据 作者ID 获取相关的图书列表
     *
     * @param authorId 作者ID
     * @return 相关的图书列表
     */
    List<BookDto> getBooksByAuthorId(Long authorId);

}
