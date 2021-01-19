package com.silentcloud.springrest.service.impl.module.lib;


import com.silentcloud.springrest.model.entity.lib.Author;
import com.silentcloud.springrest.model.entity.lib.Book;
import com.silentcloud.springrest.model.entity.lib.Publisher;
import com.silentcloud.springrest.repository.lib.AuthorRepository;
import com.silentcloud.springrest.repository.lib.BookRepository;
import com.silentcloud.springrest.repository.lib.PublisherRepository;
import com.silentcloud.springrest.service.api.dto.lib.BookDto;
import com.silentcloud.springrest.service.api.module.lib.BookService;
import com.silentcloud.springrest.service.impl.mapper.lib.BookMapper;
import com.silentcloud.springrest.service.impl.module.AbstractBaseService;
import org.jooq.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.silentcloud.springrest.jooq.gen.Tables.*;
import static com.silentcloud.springrest.service.impl.util.JooqUtil.GROUP_FIELDS_SEPARATOR;
import static org.jooq.impl.DSL.groupConcat;

@Service
@Transactional(readOnly = true)
public class BookServiceImpl extends AbstractBaseService<Long, Book, BookDto> implements BookService {
    @SuppressWarnings({"FieldCanBeLocal", "unused"})
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final PublisherRepository publisherRepository;
    private final AuthorRepository authorRepository;

    private final Field<String> authorsName = groupConcat(LIB_AUTHOR.NAME).orderBy(LIB_AUTHOR.NAME.asc())
                                                .separator(GROUP_FIELDS_SEPARATOR).as("name");
    private final Table<? extends Record> authorsGroupByBook =
            dsl.select(LIB_BOOK_AUTHOR.BOOK_ID, authorsName)
                    .from(LIB_BOOK_AUTHOR)
                    .join(LIB_AUTHOR).on(LIB_BOOK_AUTHOR.AUTHOR_ID.eq(LIB_AUTHOR.ID))
                    .groupBy(LIB_BOOK_AUTHOR.BOOK_ID)
                    .asTable("authors");

    private final SelectSelectStep<? extends Record> selectPartSql = dsl.select(authorsGroupByBook.field(authorsName))
            .select(LIB_PUBLISHER.NAME).select(LIB_BOOK.fields());

    private final Table<? extends Record> joinedTable = LIB_BOOK.join(LIB_PUBLISHER).on(LIB_BOOK.PUBLISHER_ID.eq(LIB_PUBLISHER.ID))
            .join(authorsGroupByBook).on(LIB_BOOK.ID.eq(authorsGroupByBook.field(LIB_BOOK_AUTHOR.BOOK_ID)));


    @SuppressWarnings({"SpringJavaInjectionPointsAutowiringInspection", "RedundantSuppression"})
    @Autowired
    public BookServiceImpl(DSLContext dsl,
                           BookRepository bookRepository,
                           BookMapper bookMapper,
                           PublisherRepository publisherRepository,
                           AuthorRepository authorRepository) {
        super(dsl, bookRepository, bookMapper);
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
        this.publisherRepository = publisherRepository;
        this.authorRepository = authorRepository;
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
    public List<BookDto> getBooksByPublisherId(Long publisherId) {
        Publisher publisher = publisherRepository.getOne(publisherId);
        return bookMapper.entityListToDtoList(publisher.getBooks());
    }

    @Override
    public List<BookDto> getBooksByAuthorId(Long authorId) {
        Author author = authorRepository.getOne(authorId);
        return bookMapper.entityListToDtoList(author.getBooks());
    }

}
