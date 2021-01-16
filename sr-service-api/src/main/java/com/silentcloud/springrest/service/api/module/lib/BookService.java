package com.silentcloud.springrest.service.api.module.lib;

import com.silentcloud.springrest.model.entity.lib.Book;
import com.silentcloud.springrest.service.api.dto.lib.BookDto;
import com.silentcloud.springrest.service.api.module.BaseService;

import java.util.List;

public interface BookService extends BaseService<Long, Book, BookDto> {

    List<BookDto> getBooksByPublisherId(Long publisherId);

    List<BookDto> getBooksByAuthorId(Long authorId);

}
