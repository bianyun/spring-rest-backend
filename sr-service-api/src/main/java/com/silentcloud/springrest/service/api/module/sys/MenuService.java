package com.silentcloud.springrest.service.api.module.sys;

import com.silentcloud.springrest.service.api.dto.sys.ApiPermDto;
import com.silentcloud.springrest.service.api.dto.sys.ButtonDto;
import com.silentcloud.springrest.service.api.dto.sys.MenuDto;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 菜单服务接口
 *
 * @author bianyun
 */
public interface MenuService {
    /**
     * 获取所有菜单列表
     *
     * @return 所有菜单列表
     */
    List<MenuDto> findAll();

    /**
     * 新增菜单
     *
     * @param dto 菜单DTO
     */
    void create(MenuDto dto);

    /**
     * 根据 菜单ID 更新菜单
     *
     * @param id  菜单ID
     * @param dto 菜单DTO
     */
    void updateById(Long id, MenuDto dto);

    /**
     * 根据 菜单ID 删除菜单
     *
     * @param id 菜单ID
     */
    void deleteById(Long id);

    /**
     * 根据 菜单ID 获取相关的按钮列表
     *
     * @param menuId 菜单ID
     * @return 相关的按钮列表
     */
    List<ButtonDto> getButtonsByMenuId(Long menuId);

    /**
     * 根据 菜单ID 获取相关的接口权限集合
     *
     * @param menuId 菜单ID
     * @return 相关的接口权限集合
     */
    Set<ApiPermDto> getApiPermsByMenuId(Long menuId);

    /**
     * 获取菜单表中所有的菜单权限值列表
     *
     * @return 所有的菜单权限值列表
     */
    List<String> getAllMenuPermValuesInDb();

    /**
     * 根据 菜单权限值 查找菜单
     *
     * @param value 菜单权限值
     * @return 菜单
     */
    @Nullable
    MenuDto findByValue(String value);

    /**
     * 获取菜单到接口权限值集合的MAP
     *
     * @return 菜单到接口权限值集合的MAP
     */
    Map<String, Set<String>> getMenuToApiPermValuesMap();

    /**
     * 为菜单关联接口权限集合
     *
     * @param menuPermValue   菜单权限值
     * @param apiPermValueSet 接口权限值集合
     */
    void linkApiPermsToMenu(String menuPermValue, Set<String> apiPermValueSet);
}
