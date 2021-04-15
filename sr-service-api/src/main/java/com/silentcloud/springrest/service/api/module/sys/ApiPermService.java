package com.silentcloud.springrest.service.api.module.sys;

import com.silentcloud.springrest.service.api.dto.sys.ApiPermDto;
import org.springframework.lang.Nullable;

import java.util.Collection;
import java.util.List;

/**
 * 接口权限接口
 *
 * @author bianyun
 */
public interface ApiPermService {

    /**
     * 获取所有的接口权限
     *
     * @return 所有的接口权限
     */
    List<ApiPermDto> findAll();

    /**
     * 新增接口权限
     *
     * @param dto 接口权限DTO
     */
    void create(ApiPermDto dto);

    /**
     * 更新接口权限
     *
     * @param id  接口权限ID
     * @param dto 接口权限DTO
     */
    void updateById(Long id, ApiPermDto dto);

    /**
     * 根据 接口权限ID 删除接口权限
     *
     * @param id 接口权限ID
     */
    void deleteById(Long id);

    /**
     * 根据 接口权限值 获取接口权限
     *
     * @param value 接口权限值
     * @return 接口权限
     */
    @Nullable
    ApiPermDto findByValue(String value);

    /**
     * 根据 接口权限值集合 删除接口权限
     *
     * @param apiPermValues 接口权限值集合
     */
    void deleteApiPermsByValues(Collection<String> apiPermValues);
}
