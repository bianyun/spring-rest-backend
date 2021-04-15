package com.silentcloud.springrest.service.api.query.parser;

import java.util.Set;

/**
 * 查询排序表达式解析器接口
 *
 * @param <T> 排序对象类型
 *
 * @author bianyun
 */
@SuppressWarnings("unused")
public interface QuerySortExprParser<T> {

    /**
     * 解析排序表达式
     *
     * @param sortExpression 排序表达式
     * @return 排序对象
     */
    T parseSortExpression(String sortExpression);

    /**
     * 获取所有合法的字段名称集合
     *
     * @return 所有合法的字段名称集合
     */
    Set<String> getLegalFieldNames();

}
