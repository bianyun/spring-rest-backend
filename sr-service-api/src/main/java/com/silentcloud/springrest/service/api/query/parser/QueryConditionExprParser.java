package com.silentcloud.springrest.service.api.query.parser;

import java.util.Set;

public interface QueryConditionExprParser<T> {

    T parseWhereCondition(String queryExpression);

    Set<String> getLegalFieldNames();

}
