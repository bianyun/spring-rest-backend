package com.silentcloud.springrest.service.impl.query.flat;

import com.silentcloud.springrest.service.api.dto.BaseDto;
import com.silentcloud.springrest.service.api.query.FlatQueryService;
import com.silentcloud.springrest.service.api.query.parser.QueryConditionExprParser;
import com.silentcloud.springrest.service.api.query.request.PageQueryParam;
import com.silentcloud.springrest.service.api.query.request.QueryParam;
import com.silentcloud.springrest.service.api.query.response.FlatQueryRecord;
import com.silentcloud.springrest.service.api.query.response.PageInfo;
import com.silentcloud.springrest.service.impl.query.AbstractQueryService;
import com.silentcloud.springrest.service.impl.util.JpaUtil;
import org.jooq.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Persistable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.name;

@SuppressWarnings("unused")
@Service
public class FlatQueryServiceImpl extends AbstractQueryService implements FlatQueryService {
    private final DSLContext dsl;

    @Autowired
    public FlatQueryServiceImpl(DSLContext dsl) {
        this.dsl = dsl;
    }

    @Override
    public <ID extends Serializable, Entity extends Persistable<ID>, DTO extends BaseDto<ID, Entity>>
    List<FlatQueryRecord> query(Class<Entity> entityClass, QueryParam queryParam) {
        SelectFinalStep<?> selectFinalStep = doFirstPartQuery(entityClass, queryParam);
        return doSecondPartQuery(entityClass, queryParam, selectFinalStep);
    }

    @Override
    public <ID extends Serializable, Entity extends Persistable<ID>, DTO extends BaseDto<ID, Entity>>
    PageInfo<FlatQueryRecord> pageQuery(Class<Entity> entityClass, PageQueryParam pageQueryParam) {
        SelectFinalStep<?> selectFinalStep = doFirstPartQuery(entityClass, pageQueryParam);

        int totalCount = queryTotalCount(entityClass, pageQueryParam);
        if (totalCount == 0) {
            return PageInfo.of(Collections.emptyList(), pageQueryParam, totalCount);
        }

        List<FlatQueryRecord> flatRecordList = doSecondPartQuery(entityClass, pageQueryParam, selectFinalStep);
        return PageInfo.of(flatRecordList, pageQueryParam, totalCount);
    }

    private <ID extends Serializable, Entity extends Persistable<ID>>
    SelectFinalStep<?> doFirstPartQuery(Class<Entity> entityClass, QueryParam queryParam) {
        QueryConditionExprParser<Condition> queryConditionExprParser = JpaUtil.getFlatQueryConditionExprParser(entityClass);
        Set<String> legalOuterFieldNames = queryConditionExprParser.getLegalFieldNames();
        Condition outerWhereCondition = queryConditionExprParser.parseWhereCondition(queryParam.getQueryConditionExpr());

        TableLike<? extends Record> nestedTable = JpaUtil.getJooqNestedTable(entityClass);
        SelectFinalStep<?> selectFinalStep = dsl.select().from(nestedTable).where();
        if (outerWhereCondition != null) {
            selectFinalStep = ((SelectConditionStep<?>) selectFinalStep).and(outerWhereCondition);
        }
        return selectFinalStep;
    }

    private <ID extends Serializable, Entity extends Persistable<ID>>
    List<FlatQueryRecord> doSecondPartQuery(Class<Entity> entityClass, QueryParam queryParam, SelectFinalStep<?> selectFinalStep) {
        Set<String> legalOuterFieldNames = JpaUtil.getFlatQueryConditionExprParser(entityClass).getLegalFieldNames();

        List<SortOrderField> sortOrderFields = parseSortExpression(queryParam, legalOuterFieldNames);

        List<OrderField<?>> outerOrderByFields = sortOrderFields.stream().map(sortOrderField -> {
            Field<?> field = field(name(sortOrderField.getFieldName()));
            if (sortOrderField.getDirection() == Sort.Direction.ASC) {
                return field.asc();
            } else {
                return field.desc();
            }
        }).collect(Collectors.toList());

        if (!outerOrderByFields.isEmpty()) {
            selectFinalStep = ((SelectOrderByStep<?>) selectFinalStep).orderBy(outerOrderByFields);
        }

        if (queryParam instanceof PageQueryParam) {
            int pageSize = ((PageQueryParam) queryParam).getPageSize();
            int offset = ((PageQueryParam) queryParam).getOffset();
            selectFinalStep = ((SelectLimitStep<?>) selectFinalStep).limit(pageSize).offset(offset);
        }
        return convertJooqResultToFlatRecordList(selectFinalStep.fetch());
    }

    private static List<FlatQueryRecord> convertJooqResultToFlatRecordList(Result<? extends Record> queryResult) {
        List<FlatQueryRecord> resultList = new ArrayList<>();

        for (Record record : queryResult) {
            FlatQueryRecord flatRecord = new FlatQueryRecord();
            IntStream.range(0, record.size()).forEachOrdered(i -> {
                String fieldName = Objects.requireNonNull(record.field(i)).getName();
                Object fieldValue = record.getValue(i);
                flatRecord.put(fieldName, fieldValue);
            });
            resultList.add(flatRecord);
        }

        return resultList;
    }

    private <ID extends Serializable, Entity extends Persistable<ID>>
    int queryTotalCount(Class<Entity> entityClass, QueryParam queryParam) {
        Condition outerWhereCondition = JpaUtil.getFlatQueryConditionExprParser(entityClass)
                .parseWhereCondition(queryParam.getQueryConditionExpr());

        TableLike<? extends Record> nestedTable = JpaUtil.getJooqNestedTable(entityClass);
        SelectConditionStep<Record1<Integer>> scStep = dsl.selectCount().from(nestedTable).where();
        if (outerWhereCondition != null) {
            scStep = scStep.and(outerWhereCondition);
        }
        return Objects.requireNonNull(scStep.fetchOne()).value1();
    }
}
