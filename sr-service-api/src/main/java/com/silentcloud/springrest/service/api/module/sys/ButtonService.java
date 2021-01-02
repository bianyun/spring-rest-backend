package com.silentcloud.springrest.service.api.module.sys;

import com.silentcloud.springrest.model.entity.sys.Button;
import com.silentcloud.springrest.service.api.dto.sys.ApiPermDto;
import com.silentcloud.springrest.service.api.dto.sys.ButtonDto;
import com.silentcloud.springrest.service.api.module.BaseService;

import java.util.Set;

public interface ButtonService extends BaseService<Long, Button, ButtonDto> {

    Set<ApiPermDto> getApiPermsByButtonId(Long buttonId);

}
