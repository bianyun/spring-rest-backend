package com.silentcloud.springrest.repository.sys;

import com.silentcloud.springrest.model.entity.sys.Button;
import com.silentcloud.springrest.repository.BaseRepository;

import java.util.List;

/**
 * 按钮权限 Repository
 *
 * @author bianyun
 */
public interface ButtonRepository extends BaseRepository<Long, Button> {

    /**
     * 获取所有按钮权限（按显示顺序排序）
     *
     * @return 所有按钮权限
     */
    List<Button> findAllByOrderByShowOrder();

    /**
     * 根据 按钮权限值 查找按钮权限
     * @param value 按钮权限值
     * @return 按钮权限
     */
    Button findByValue(String value);

}
