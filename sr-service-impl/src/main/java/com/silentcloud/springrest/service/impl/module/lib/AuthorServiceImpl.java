package com.silentcloud.springrest.service.impl.module.lib;

import com.silentcloud.springrest.model.entity.lib.Author;
import com.silentcloud.springrest.repository.lib.AuthorRepository;
import com.silentcloud.springrest.service.api.dto.lib.AuthorDto;
import com.silentcloud.springrest.service.api.module.lib.AuthorService;
import com.silentcloud.springrest.service.impl.mapper.lib.AuthorMapper;
import com.silentcloud.springrest.service.impl.module.AbstractBaseService;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.silentcloud.springrest.jooq.gen.Tables.LIB_AUTHOR;

@SuppressWarnings({"FieldCanBeLocal", "unused"})
@Service
@Transactional(readOnly = true)
public class AuthorServiceImpl extends AbstractBaseService<Long, Author, AuthorDto> implements AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @SuppressWarnings({"SpringJavaInjectionPointsAutowiringInspection", "RedundantSuppression"})
    @Autowired
    public AuthorServiceImpl(DSLContext dsl,
                             AuthorRepository authorRepository,
                             AuthorMapper authorMapper) {
        super(dsl, authorRepository, authorMapper);
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    @Override
    protected Table<? extends Record> buildJoinedTable() {
        return LIB_AUTHOR;
    }
}
