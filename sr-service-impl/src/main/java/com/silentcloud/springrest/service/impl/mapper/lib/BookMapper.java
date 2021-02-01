package com.silentcloud.springrest.service.impl.mapper.lib;

import com.silentcloud.springrest.model.entity.lib.Book;
import com.silentcloud.springrest.service.api.dto.lib.BookDto;
import com.silentcloud.springrest.service.impl.mapper.BaseMapper;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import static com.silentcloud.springrest.service.impl.util.JpaUtil.getReferencedEntities;
import static com.silentcloud.springrest.service.impl.util.JpaUtil.getReferencedEntity;

@SuppressWarnings({"SpringJavaAutowiredMembersInspection", "RedundantSuppression"})
@Mapper
public abstract class BookMapper implements BaseMapper<Long, Book, BookDto> {

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
        entity.setPublisher(getReferencedEntity(dto.getPublisher()));
        entity.setAuthors(getReferencedEntities(dto.getAuthors()));
    }

}

