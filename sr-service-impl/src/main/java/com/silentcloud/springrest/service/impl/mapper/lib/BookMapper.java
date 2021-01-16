package com.silentcloud.springrest.service.impl.mapper.lib;

import com.silentcloud.springrest.model.entity.lib.Author;
import com.silentcloud.springrest.model.entity.lib.Book;
import com.silentcloud.springrest.repository.lib.AuthorRepository;
import com.silentcloud.springrest.repository.lib.PublisherRepository;
import com.silentcloud.springrest.service.api.dto.lib.BookDto;
import com.silentcloud.springrest.service.impl.mapper.BaseMapper;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

@SuppressWarnings({"SpringJavaAutowiredMembersInspection", "RedundantSuppression"})
@Mapper
public abstract class BookMapper implements BaseMapper<Long, Book, BookDto> {

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Mapping(target = "authorIds", ignore = true)
    @Mapping(target = "publisherId", source = "publisher.id")
    @Override
    public abstract BookDto entityToDto(Book entity);

    @Mapping(target = "lastModifiedTime", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "createdTime", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "publisher", ignore = true)
    @Mapping(target = "authors", ignore = true)
    @Override
    public abstract Book dtoToEntity(BookDto bookDto);

    @Mapping(target = "lastModifiedTime", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "createdTime", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "publisher", ignore = true)
    @Mapping(target = "authors", ignore = true)
    @Override
    public abstract void updateEntityFromDto(BookDto bookDto, @MappingTarget Book book);

    @AfterMapping
    public void establishRelations(BookDto dto, @MappingTarget Book entity) {
        entity.setPublisher(publisherRepository.getOne(dto.getPublisherId()));
        entity.setAuthors(dto.getAuthorIds().stream()
                .map(authorId -> authorRepository.getOne(authorId))
                .collect(Collectors.toList()));
    }

    @AfterMapping
    public void addUnmappedStuff(Book entity, @MappingTarget BookDto dto) {
        dto.setAuthorIds(entity.getAuthors().stream().map(Author::getId).collect(Collectors.toSet()));
    }
}

