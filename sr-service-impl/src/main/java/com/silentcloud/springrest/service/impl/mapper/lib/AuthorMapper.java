package com.silentcloud.springrest.service.impl.mapper.lib;

import com.silentcloud.springrest.model.entity.lib.Author;
import com.silentcloud.springrest.service.api.dto.lib.AuthorDto;
import com.silentcloud.springrest.service.impl.mapper.BaseMapper;
import org.mapstruct.Mapper;

/**
 * 作者 Mapper
 *
 * @author bianyun
 */
@Mapper
public interface AuthorMapper extends BaseMapper<Long, Author, AuthorDto> {
}

