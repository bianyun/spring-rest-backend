package com.silentcloud.springrest.service.impl.mapper.lib;

import com.silentcloud.springrest.model.entity.lib.Book;
import com.silentcloud.springrest.repository.lib.AuthorRepository;
import com.silentcloud.springrest.repository.lib.PublisherRepository;
import com.silentcloud.springrest.repository.lib.TranslaterRepository;
import com.silentcloud.springrest.service.api.dto.lib.BookDto;
import com.silentcloud.springrest.service.impl.mapper.BaseMapper;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

@Mapper
public abstract class BookMapper implements BaseMapper<Long, Book, BookDto> {

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private TranslaterRepository translaterRepository;

    @Mapping(target = "lastModifiedTime", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "createdTime", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "publisher", ignore = true)
    @Mapping(target = "authors", ignore = true)
    @Mapping(target = "translaters", ignore = true)
    @Override
    public abstract Book dtoToEntity(BookDto bookDto);

    @Mapping(target = "lastModifiedTime", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "createdTime", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "publisher", ignore = true)
    @Mapping(target = "authors", ignore = true)
    @Mapping(target = "translaters", ignore = true)
    @Override
    public abstract void updateEntityFromDto(BookDto bookDto, @MappingTarget Book book);

    @AfterMapping
    public void establishRelations(BookDto dto, @MappingTarget Book book) {
        book.setPublisher(publisherRepository.getOne(dto.getPublisher().getId()));
        book.setAuthors(dto.getAuthors().stream()
                .map(authorDto -> authorRepository.getOne(authorDto.getId()))
                .collect(Collectors.toList()));
        book.setTranslaters(dto.getTranslaters().stream()
                .map(translaterDto -> translaterRepository.getOne(translaterDto.getId()))
                .collect(Collectors.toList()));
    }

}

