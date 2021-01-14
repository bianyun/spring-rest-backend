package com.silentcloud.springrest.service.api.module.sys;

import com.silentcloud.springrest.service.api.dto.sys.ApiPermDto;
import org.springframework.lang.Nullable;

import java.util.Collection;
import java.util.List;

public interface ApiPermService {

    List<ApiPermDto> findAll();

    void create(ApiPermDto dto);

    void updateById(Long id, ApiPermDto dto);

    void deleteById(Long id);

    @Nullable
    ApiPermDto findByValue(String value);

    void deleteApiPermsByValues(Collection<String> apiPermValues);
}
