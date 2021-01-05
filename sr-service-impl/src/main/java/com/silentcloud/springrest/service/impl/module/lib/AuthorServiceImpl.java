package com.silentcloud.springrest.service.impl.module.lib;

import com.silentcloud.springrest.model.entity.lib.Author;
import com.silentcloud.springrest.repository.lib.AuthorRepository;
import com.silentcloud.springrest.service.api.dto.lib.AuthorDto;
import com.silentcloud.springrest.service.api.module.lib.AuthorService;
import com.silentcloud.springrest.service.impl.mapper.lib.AuthorMapper;
import com.silentcloud.springrest.service.impl.module.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
public class AuthorServiceImpl extends AbstractBaseService<Long, Author, AuthorDto> implements AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository,
                             AuthorMapper authorMapper) {
        super(authorRepository, authorMapper);
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

}
