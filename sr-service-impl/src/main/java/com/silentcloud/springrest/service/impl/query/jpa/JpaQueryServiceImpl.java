package com.silentcloud.springrest.service.impl.query.jpa;

import com.github.wenhao.jpa.PredicateBuilder;
import com.github.wenhao.jpa.Specifications;
import com.silentcloud.springrest.repository.BaseRepository;
import com.silentcloud.springrest.service.api.dto.BaseDto;
import com.silentcloud.springrest.service.api.query.JpaQueryService;
import com.silentcloud.springrest.service.api.query.parser.QueryConditionExprParser;
import com.silentcloud.springrest.service.api.query.request.PageQueryParam;
import com.silentcloud.springrest.service.api.query.request.QueryParam;
import com.silentcloud.springrest.service.api.query.response.PageInfo;
import com.silentcloud.springrest.service.impl.meta.EntityRepositoryMap;
import com.silentcloud.springrest.service.impl.query.AbstractQueryService;
import com.silentcloud.springrest.service.impl.util.JpaUtil;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.silentcloud.springrest.model.entity.LogicallyDeletable.DELETED_PROPERTY_NAME;

/**
 * JPA查询服务实现
 *
 * @author bianyun
 */
@SuppressWarnings("unchecked")
@Service
@Transactional(readOnly = true)
public class JpaQueryServiceImpl extends AbstractQueryService implements JpaQueryService {

    @Override
    public <ID extends Serializable, Entity extends Persistable<ID>, DTO extends BaseDto<ID, Entity>>
    List<DTO> query(Class<Entity> entityClass, QueryParam queryParam) {
        List<Entity> entities = doPageQuery(entityClass, queryParam).getContent();
        return (List<DTO>) JpaUtil.getMapper(entityClass).entityListToDtoList(entities);
    }

    @Override
    public <ID extends Serializable, Entity extends Persistable<ID>, DTO extends BaseDto<ID, Entity>>
    PageInfo<DTO> pageQuery(Class<Entity> entityClass, PageQueryParam pageQueryParam) {
        Page<Entity> page = doPageQuery(entityClass, pageQueryParam);
        return (PageInfo<DTO>) PageInfo.fromPage(page, pageQueryParam, JpaUtil.getMapper(entityClass)::entityToDto);
    }

    private <ID extends Serializable, Entity extends Persistable<ID>>
    Page<Entity> doPageQuery(Class<Entity> entityClass, QueryParam queryParam) {
        QueryConditionExprParser<Specification<Entity>> queryConditionExprParser = JpaUtil.getJpaQueryConditionExprParser(entityClass);
        boolean isEntityLogicallyDeletable = JpaUtil.isEntityLogicallyDeletable(entityClass);
        PredicateBuilder<Entity> predicateBuilder = Specifications.<Entity>and().eq(isEntityLogicallyDeletable, DELETED_PROPERTY_NAME, false);

        Specification<Entity> queryParamSpec = queryConditionExprParser.parseWhereCondition(queryParam.getQueryConditionExpr());
        if (queryParamSpec != null) {
            predicateBuilder = predicateBuilder.predicate(queryParamSpec);
        }
        Specification<Entity> specification = predicateBuilder.build();

        BaseRepository<ID, Entity> repository = EntityRepositoryMap.getRepository(entityClass);

        Set<String> legalPropNames = JpaUtil.getLegalPropNamesCanBeUsedInJpaQueryExpr(entityClass);
        List<SortOrderField> sortOrderFields = parseSortExpression(queryParam, legalPropNames);

        List<Sort.Order> sortOrderList = sortOrderFields.stream().map(sortOrderField -> {
            if (sortOrderField.getDirection() == Sort.Direction.ASC) {
                return Sort.Order.asc(sortOrderField.getFieldName());
            } else {
                return Sort.Order.desc(sortOrderField.getFieldName());
            }
        }).collect(Collectors.toList());

        return repository.findAll(specification, resolvePageable(queryParam, sortOrderList));
    }

    private Pageable resolvePageable(QueryParam queryParam, List<Sort.Order> sortOrderList) {
        int page;
        int size;
        if (queryParam instanceof PageQueryParam) {
            PageQueryParam pageQueryParam = (PageQueryParam) queryParam;
            page = pageQueryParam.getPageNumber() - 1;
            size = pageQueryParam.getPageSize();
        } else {
            page = 0;
            size = Integer.MAX_VALUE;
        }
        return PageRequest.of(page, size, Sort.by(sortOrderList));
    }
}
