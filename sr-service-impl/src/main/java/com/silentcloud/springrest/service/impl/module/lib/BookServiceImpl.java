package com.silentcloud.springrest.service.impl.module.lib;


import com.silentcloud.springrest.model.entity.lib.Author;
import com.silentcloud.springrest.model.entity.lib.Book;
import com.silentcloud.springrest.model.entity.lib.Publisher;
import com.silentcloud.springrest.model.entity.lib.Translater;
import com.silentcloud.springrest.repository.lib.AuthorRepository;
import com.silentcloud.springrest.repository.lib.BookRepository;
import com.silentcloud.springrest.repository.lib.PublisherRepository;
import com.silentcloud.springrest.repository.lib.TranslaterRepository;
import com.silentcloud.springrest.service.api.dto.lib.BookDto;
import com.silentcloud.springrest.service.api.module.lib.BookService;
import com.silentcloud.springrest.service.impl.mapper.lib.BookMapper;
import com.silentcloud.springrest.service.impl.module.AbstractBaseService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
public class BookServiceImpl extends AbstractBaseService<Long, Book, BookDto> implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final PublisherRepository publisherRepository;
    private final AuthorRepository authorRepository;
    private final TranslaterRepository translaterRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository,
                           BookMapper bookMapper,
                           PublisherRepository publisherRepository,
                           AuthorRepository authorRepository,
                           TranslaterRepository translaterRepository) {
        super(bookRepository, bookMapper);
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
        this.publisherRepository = publisherRepository;
        this.authorRepository = authorRepository;
        this.translaterRepository = translaterRepository;
    }

    @Override
    public List<BookDto> getBooksByPublisherId(@NonNull Long publisherId) {
        Publisher publisher = publisherRepository.getOne(publisherId);
        return bookMapper.entityListToDtoList(publisher.getBooks());
    }

    @Override
    public List<BookDto> getBooksByAuthorId(@NonNull Long authorId) {
        Author author = authorRepository.getOne(authorId);
        return bookMapper.entityListToDtoList(author.getBooks());
    }

    @Override
    public List<BookDto> getBooksByTranslaterId(@NonNull Long translaterId) {
        Translater translater = translaterRepository.getOne(translaterId);
        return bookMapper.entityListToDtoList(translater.getBooks());
    }
}
