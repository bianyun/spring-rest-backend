package com.silentcloud.springrest.repository.sys;

import com.silentcloud.springrest.model.entity.sys.Menu;
import com.silentcloud.springrest.repository.BaseRepository;

import java.util.List;

/**
 * 菜单权限 Repository
 *
 * @author bianyun
 */
public interface MenuRepository extends BaseRepository<Long, Menu> {

    /**
     * 获取所有菜单权限列表（按菜单权限值排序）
     *
     * @return 所有菜单权限列表
     */
    List<Menu> findAllByOrderByValue();

    /**
     * 根据 菜单权限值 查找菜单权限
     * @param value 菜单权限值
     * @return 菜单权限
     */
    Menu findByValue(String value);

}
