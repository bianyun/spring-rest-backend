package com.silentcloud.springrest.service.api.module.sys;

import com.silentcloud.springrest.model.entity.sys.Button;
import com.silentcloud.springrest.service.api.dto.sys.ApiPermDto;
import com.silentcloud.springrest.service.api.dto.sys.ButtonDto;
import com.silentcloud.springrest.service.api.module.BaseService;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 按钮服务接口
 *
 * @author bianyun
 */
public interface ButtonService extends BaseService<Long, Button, ButtonDto> {

    /**
     * 根据 按钮ID 获取相关的接口权限集合
     *
     * @param buttonId 按钮ID
     * @return 相关的接口权限集合
     */
    Set<ApiPermDto> getApiPermsByButtonId(Long buttonId);

    /**
     * 获取菜单到按钮权限列表的MAP
     *
     * @return 菜单到按钮权限列表的MAP
     */
    Map<String, List<ButtonDto>> getMenuToButtonPermsMap();
}
