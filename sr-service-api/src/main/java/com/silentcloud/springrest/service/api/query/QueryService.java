package com.silentcloud.springrest.service.api.query;

import com.silentcloud.springrest.service.api.dto.BaseDto;
import com.silentcloud.springrest.service.api.query.request.PageQueryParam;
import com.silentcloud.springrest.service.api.query.request.QueryParam;
import com.silentcloud.springrest.service.api.query.response.PageInfo;
import org.springframework.data.domain.Persistable;

import java.io.Serializable;
import java.util.List;

/**
 * 查询服务
 *
 * @author bianyun
 */
public interface QueryService {

    /**
     * 查询
     *
     * @param entityClass 实体类型
     * @param queryParam  查询参数
     * @param <ID>        实体ID类型
     * @param <Entity>    实体类型
     * @param <DTO>       实体类对应的 DTO类型
     * @return 查询结果
     */
    <ID extends Serializable, Entity extends Persistable<ID>, DTO extends BaseDto<ID, Entity>>
    List<?> query(Class<Entity> entityClass, QueryParam queryParam);

    /**
     * 分页查询
     *
     * @param entityClass    实体类型
     * @param pageQueryParam 分页查询参数
     * @param <ID>           实体ID类型
     * @param <Entity>       实体类型
     * @param <DTO>          实体类对应的 DTO类型
     * @return 查询结果
     */
    <ID extends Serializable, Entity extends Persistable<ID>, DTO extends BaseDto<ID, Entity>>
    PageInfo<?> pageQuery(Class<Entity> entityClass, PageQueryParam pageQueryParam);

}
