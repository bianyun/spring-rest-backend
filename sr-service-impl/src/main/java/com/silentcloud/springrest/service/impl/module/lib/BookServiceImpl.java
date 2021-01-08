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
import org.jooq.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.silentcloud.springrest.jooq.gen.Tables.*;
import static org.jooq.impl.DSL.groupConcat;

@Service
@Transactional(readOnly = true)
public class BookServiceImpl extends AbstractBaseService<Long, Book, BookDto> implements BookService {
    @SuppressWarnings({"FieldCanBeLocal", "unused"})
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final PublisherRepository publisherRepository;
    private final AuthorRepository authorRepository;
    private final TranslaterRepository translaterRepository;

    private final Field<String> authorsName = groupConcat(LIB_AUTHOR.NAME).orderBy(LIB_AUTHOR.NAME.asc()).separator(",").as("name");
    private final Table<? extends Record> authorsGroupByBook =
            dsl.select(LIB_BOOK_AUTHOR.BOOK_ID, authorsName)
                    .from(LIB_BOOK_AUTHOR)
                    .join(LIB_AUTHOR).on(LIB_BOOK_AUTHOR.AUTHOR_ID.eq(LIB_AUTHOR.ID))
                    .groupBy(LIB_BOOK_AUTHOR.BOOK_ID)
                    .asTable("authors");

    private final Field<String> translatersName = groupConcat(LIB_TRANSLATER.NAME).orderBy(LIB_TRANSLATER.NAME.asc()).separator(",").as("name");
    private final Table<? extends Record> translatersGroupByBook =
            dsl.select(LIB_BOOK_TRANSLATER.BOOK_ID, translatersName)
                    .from(LIB_BOOK_TRANSLATER)
                    .join(LIB_TRANSLATER).on(LIB_BOOK_TRANSLATER.TRANSLATER_ID.eq(LIB_TRANSLATER.ID))
                    .groupBy(LIB_BOOK_TRANSLATER.BOOK_ID)
                    .asTable("translaters");

    private final SelectSelectStep<? extends Record> selectPartSql = dsl.select(authorsGroupByBook.field(authorsName))
            .select(translatersGroupByBook.field(translatersName))
            .select(LIB_BOOK.fields());

    private final Table<? extends Record> joinedTable = LIB_BOOK.join(authorsGroupByBook).on(LIB_BOOK.ID.eq(authorsGroupByBook.field(LIB_BOOK_AUTHOR.BOOK_ID)))
            .leftJoin(translatersGroupByBook).on(LIB_BOOK.ID.eq(translatersGroupByBook.field(LIB_BOOK_TRANSLATER.BOOK_ID)));


    @Autowired
    public BookServiceImpl(DSLContext dsl,
                           BookRepository bookRepository,
                           BookMapper bookMapper,
                           PublisherRepository publisherRepository,
                           AuthorRepository authorRepository,
                           TranslaterRepository translaterRepository) {
        super(dsl, bookRepository, bookMapper);
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
        this.publisherRepository = publisherRepository;
        this.authorRepository = authorRepository;
        this.translaterRepository = translaterRepository;
    }

    @Override
    protected SelectSelectStep<? extends Record> buildSelectPartSql() {
        return selectPartSql;
    }

    @Override
    protected Table<? extends Record> buildJoinedTable() {
        return joinedTable;
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
