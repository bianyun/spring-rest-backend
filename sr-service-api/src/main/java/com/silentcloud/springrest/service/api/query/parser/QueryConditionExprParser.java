package com.silentcloud.springrest.service.api.query.parser;

import org.springframework.lang.Nullable;

import java.util.Set;

/**
 * 查询条件表达式解析器接口
 *
 * @param <T> Where条件对象类型
 * @author bianyun
 */
public interface QueryConditionExprParser<T> {

    /**
     * 解析 Where条件
     *
     * @param queryExpression 条件表达式
     * @return Where条件对象
     */
    @Nullable
    T parseWhereCondition(String queryExpression);

    /**
     * 获取所有合法的字段名称集合
     *
     * @return 所有合法的字段名称集合
     */
    Set<String> getLegalFieldNames();

}
