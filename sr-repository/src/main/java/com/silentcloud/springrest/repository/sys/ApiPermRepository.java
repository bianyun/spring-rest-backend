package com.silentcloud.springrest.repository.sys;

import com.silentcloud.springrest.model.entity.sys.ApiPerm;
import com.silentcloud.springrest.repository.BaseRepository;

/**
 * 接口权限 Repository
 *
 * @author bianyun
 */
public interface ApiPermRepository extends BaseRepository<Long, ApiPerm> {

    /**
     * 根据 接口权限值 查找接口权限
     * @param value 接口权限值
     * @return 接口权限
     */
    ApiPerm findByValue(String value);

}
