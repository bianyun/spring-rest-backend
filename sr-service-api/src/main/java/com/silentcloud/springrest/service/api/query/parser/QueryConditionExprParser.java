package com.silentcloud.springrest.service.api.query.parser;

import org.springframework.lang.Nullable;

import java.util.Set;

public interface QueryConditionExprParser<T> {

    @Nullable
    T parseWhereCondition(String queryExpression);

    Set<String> getLegalFieldNames();

}
