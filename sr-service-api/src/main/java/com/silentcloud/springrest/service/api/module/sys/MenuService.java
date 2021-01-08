package com.silentcloud.springrest.service.api.module.sys;

import com.silentcloud.springrest.service.api.dto.sys.ApiPermDto;
import com.silentcloud.springrest.service.api.dto.sys.ButtonDto;
import com.silentcloud.springrest.service.api.dto.sys.MenuDto;

import java.util.List;
import java.util.Set;

public interface MenuService {
    List<MenuDto> findAll();

    MenuDto create(MenuDto dto);

    void updateById(Long id, MenuDto dto);

    void deleteById(Long aLong);

    MenuDto findById(Long id);

    List<ButtonDto> getButtonsByMenuId(Long menuId);

    Set<ApiPermDto> getApiPermsByMenuId(Long menuId);

    List<String> getAllMenuPermValuesInDb();

    MenuDto findByValue(String value);
}
