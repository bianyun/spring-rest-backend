package com.silentcloud.springrest.service.api.module;

import com.silentcloud.springrest.service.api.dto.BaseDto;
import org.springframework.data.domain.Persistable;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * 公共服务接口
 *
 * @param <ID>     实体ID类型
 * @param <Entity> 实体类型
 * @param <DTO>    DTO类型
 * @author bianyun
 */
public interface BaseService<ID extends Serializable, Entity extends Persistable<ID>, DTO extends BaseDto<ID, Entity>> {

    /**
     * 检查实体对象的ID是否存在
     *
     * @param id 实体对象ID
     * @return 实体对象的ID是否存在
     */
    boolean existsById(ID id);

    /**
     * 根据 实体对象ID 查找 DTO对象
     *
     * @param id 实体对象ID
     * @return DTO对象
     */
    @Nullable
    DTO findById(ID id);

    /**
     * 获取所有实体对应的 DTO对象列表
     *
     * @return 所有实体对应的 DTO对象列表
     */
    List<DTO> findAll();

    /**
     * 新增实体对象
     *
     * @param dto DTO对象
     * @return DTO对象（已填充ID信息）
     */
    DTO create(DTO dto);

    /**
     * 更新实体对象
     *
     * @param id  实体对象ID
     * @param dto DTO对象
     * @return 已更新的 DTO对象
     */
    DTO updateById(ID id, DTO dto);

    /**
     * 根据实体对象ID 删除实体对象
     *
     * @param id 实体对象ID
     */
    void deleteById(ID id);

    /**
     * 批量删除实体对象
     *
     * @param idSet 实体对象ID集合
     */
    void batchDeleteByIdSet(Set<ID> idSet);

    /**
     * 根据 实体对象ID 启用实体对象
     *
     * @param id 实体对象ID
     */
    void activateById(ID id);

    /**
     * 根据 实体对象ID 停用实体对象
     *
     * @param id 实体对象ID
     */
    void deactivateById(ID id);

}
