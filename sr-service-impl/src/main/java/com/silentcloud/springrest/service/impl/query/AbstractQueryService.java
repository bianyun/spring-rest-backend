package com.silentcloud.springrest.service.impl.query;

import cn.hutool.core.util.StrUtil;
import com.silentcloud.springrest.service.api.query.QueryService;
import com.silentcloud.springrest.service.api.query.request.QueryParam;
import lombok.Value;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public abstract class AbstractQueryService implements QueryService {

    protected List<SortOrderField> parseSortExpression(QueryParam queryParam, Set<String> legalFieldNames) {

        String querySortExpr = queryParam.getQuerySortExpr();
        if (StrUtil.isBlank(querySortExpr)) {
            return Collections.emptyList();
        }

        List<SortOrderField> resultList = new ArrayList<>();

        String regex = "^\\s*([0-9a-z-A-Z_.]+(\\s+\\S+)?)(\\s*,\\s*[0-9a-z-A-Z_.]+(\\s+\\S+)?)?\\s*$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        if (!pattern.matcher(querySortExpr).find()) {
            throw illegalSortExpression(querySortExpr);
        }

        String[] fieldStrArray = querySortExpr.trim().split("\\s*,\\s*");
        for (String fieldStr : fieldStrArray) {
            String[] fieldParts = fieldStr.trim().split("\\s+");
            if (fieldParts.length < 1 || fieldParts.length > 2) {
                throw illegalSortExpression(querySortExpr);
            }

            String fieldName = fieldParts[0];
            if (!legalFieldNames.contains(fieldName)) {
                throw illegalSortExpression("查询排序表达式不合法: 非法的字段名[{}]", fieldName);
            }

            SortOrderField sortOrderField;
            if (fieldParts.length == 1) {
                // no order keyword, use default ASC order
                sortOrderField = SortOrderField.of(fieldName, Sort.Direction.ASC);
            } else {
                // order keyword specified, parse it
                String order = fieldParts[1];

                if (order.equalsIgnoreCase("asc")) {
                    sortOrderField = SortOrderField.of(fieldName, Sort.Direction.ASC);
                } else if (order.equalsIgnoreCase("desc")) {
                    sortOrderField = SortOrderField.of(fieldName, Sort.Direction.DESC);
                } else {
                    throw illegalSortExpression("查询排序表达式不合法: 非法的排序顺序关键字[{}]", order);
                }
            }
            resultList.add(sortOrderField);

        }

        return resultList;
    }

    @Value(staticConstructor = "of")
    protected static class SortOrderField {
        String fieldName;
        Sort.Direction direction;
    }

    private static IllegalArgumentException illegalSortExpression(String orderByExpression) {
        return new IllegalArgumentException(StrUtil.format("查询排序表达式不合法: [{}]", orderByExpression));
    }

    private static IllegalArgumentException illegalSortExpression(String msgTemplate, Object... args) {
        return new IllegalArgumentException(StrUtil.format(msgTemplate, args));
    }
}
