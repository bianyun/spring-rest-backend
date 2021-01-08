package com.silentcloud.springrest.service.api.query;

import com.silentcloud.springrest.service.api.dto.BaseDto;
import com.silentcloud.springrest.service.api.query.request.PageQueryParam;
import com.silentcloud.springrest.service.api.query.request.QueryParam;
import com.silentcloud.springrest.service.api.query.response.FlatQueryRecord;
import com.silentcloud.springrest.service.api.query.response.PageInfo;
import org.springframework.data.domain.Persistable;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("unused")
public interface FlatQueryService extends QueryService {

    <ID extends Serializable, Entity extends Persistable<ID>, DTO extends BaseDto<ID, Entity>>
    List<FlatQueryRecord> query(Class<Entity> entityClass, QueryParam queryParam);

    <ID extends Serializable, Entity extends Persistable<ID>, DTO extends BaseDto<ID, Entity>>
    PageInfo<FlatQueryRecord> pageQuery(Class<Entity> entityClass, PageQueryParam pageQueryParam);

}
