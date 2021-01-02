package com.silentcloud.springrest.service.api.module.sys;

import com.silentcloud.springrest.model.entity.sys.Button;
import com.silentcloud.springrest.service.api.dto.sys.ApiPermDto;
import com.silentcloud.springrest.service.api.dto.sys.ButtonDto;
import com.silentcloud.springrest.service.api.module.BaseService;

import java.util.List;
import java.util.Set;

public interface MenuService extends BaseService<Long, Button, ButtonDto> {

    List<ButtonDto> getButtonsByMenuId(Long menuId);

    Set<ApiPermDto> getApiPermsByMenuId(Long menuId);

}
