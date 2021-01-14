package com.silentcloud.springrest.service.api.query.parser;

import java.util.Set;

@SuppressWarnings("unused")
public interface QuerySortExprParser<T> {

    T parseSortExpression(String sortExpression);

    Set<String> getLegalFieldNames();

}
