package com.silentcloud.springrest.service.api.module.sys;

import com.silentcloud.springrest.service.api.dto.sys.ApiPermDto;

import java.util.List;

public interface ApiPermService {

    List<ApiPermDto> findAll();

    ApiPermDto create(ApiPermDto dto);

    void deleteById(Long aLong);

    ApiPermDto findById(Long id);
}
