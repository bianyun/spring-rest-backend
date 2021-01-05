package com.silentcloud.springrest.service.impl.mapper.lib;

import com.silentcloud.springrest.model.entity.lib.Translater;
import com.silentcloud.springrest.service.api.dto.lib.TranslaterDto;
import com.silentcloud.springrest.service.impl.mapper.BaseMapper;
import org.mapstruct.Mapper;

@Mapper
public interface TranslaterMapper extends BaseMapper<Long, Translater, TranslaterDto> {
}

