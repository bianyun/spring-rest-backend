package com.silentcloud.springrest.service.impl.query;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.silentcloud.springrest.service.api.query.IllegalQueryExprException;
import com.silentcloud.springrest.service.api.query.IllegalQueryExprException.ErrorType;
import com.silentcloud.springrest.service.api.query.parser.QueryConditionExprParser;
import com.silentcloud.springrest.service.impl.query.antlr.gen.QueryExpressionBaseVisitor;
import com.silentcloud.springrest.service.impl.query.antlr.gen.QueryExpressionLexer;
import com.silentcloud.springrest.service.impl.query.antlr.gen.QueryExpressionParser;
import com.silentcloud.springrest.service.impl.query.flat.FlatQueryConditionExprParser;
import com.silentcloud.springrest.service.impl.util.JooqUtil;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;

import java.util.Set;

import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.name;

public abstract class AbstractQueryConditionExprParser<T> extends QueryExpressionBaseVisitor<T>
        implements QueryConditionExprParser<T> {
    private final Set<String> legalFieldNames;
    private final String queryType;

    protected AbstractQueryConditionExprParser(String queryType, Set<String> legalFieldNames) {
        this.legalFieldNames = legalFieldNames;
        this.queryType = queryType;
    }

    @Override
    public Set<String> getLegalFieldNames() {
        return legalFieldNames;
    }

    @Override
    public T parseWhereCondition(String queryExpression) {
        if (StrUtil.isBlank(queryExpression)) {
            return null;
        }

        TokenStream input = new CommonTokenStream(
                new QueryExpressionLexer(CharStreams.fromString(queryExpression.trim())));
        QueryExpressionParser parser = new QueryExpressionParser(input);
        QueryExpressionParser.ExprContext context = parser.expr();
        return this.visit(context);
    }

    protected enum ValueType {
        STRING, INTEGER, LONG, DOUBLE, BOOLEAN, LITERAL_NULL, FIELD
    }

    protected IllegalQueryExprException illegalQueryExprException(String description) {
        return new IllegalQueryExprException(queryType, description);
    }

    protected IllegalQueryExprException illegalQueryExprException(ErrorType errorType, String errorValue) {
        return new IllegalQueryExprException(queryType, errorType, errorValue);
    }

    protected void checkFieldNameValidity(String fieldName) {
        if (!getLegalFieldNames().contains(fieldName)) {
            throw illegalQueryExprException(ErrorType.FIELD, fieldName);
        }
    }

    @Override
    public T visitCondition(QueryExpressionParser.ConditionContext ctx) {
        String fieldName = ctx.field().getText().toLowerCase();
        checkFieldNameValidity(fieldName);
        String queryExpression = ctx.getText().replace("<missing STRING>", "");

        String valueStr = ctx.value().getText().trim();
        if (StrUtil.equals(valueStr, "<missing STRING>")) {
            throw illegalQueryExprException(queryExpression);
        }
        ValueType valueType;

        if ((valueStr.startsWith("'") && !valueStr.endsWith("'")) ||
                (!valueStr.startsWith("'") && valueStr.endsWith("'"))) {
            throw illegalQueryExprException(queryExpression);
        } else if (valueStr.startsWith("'") && valueStr.endsWith("'")) {
            valueStr = valueStr.substring(1, valueStr.length() - 1).trim();
            valueType = ValueType.STRING;
        } else {
            if (NumberUtil.isInteger(valueStr)) {
                valueType = ValueType.INTEGER;
            } else if (NumberUtil.isLong(valueStr)) {
                valueType = ValueType.LONG;
            } else if (NumberUtil.isDouble(valueStr)) {
                valueType = ValueType.DOUBLE;
            } else if (StrUtil.equalsAnyIgnoreCase(valueStr, "true", "false")) {
                valueType = ValueType.BOOLEAN;
            } else if (valueStr.equalsIgnoreCase("null")) {
                valueType = ValueType.LITERAL_NULL;
            } else if (valueStr.contains(JooqUtil.DELIMETER_BETWEEN_TABLE_AND_COLUMN)
                    && this instanceof FlatQueryConditionExprParser) {
                valueType = ValueType.FIELD;
                checkFieldNameValidity(valueStr);
            } else {
                throw illegalQueryExprException(queryExpression);
            }
        }

        Object value = null;
        if (valueType == ValueType.STRING) {
            value = valueStr;
        } else if (valueType == ValueType.INTEGER) {
            value = Integer.valueOf(valueStr);
        } else if (valueType == ValueType.LONG) {
            value = Long.valueOf(valueStr);
        } else if (valueType == ValueType.DOUBLE) {
            value = Double.valueOf(valueStr);
        } else if (valueType == ValueType.BOOLEAN) {
            value = Boolean.valueOf(valueStr);
        } else if (valueType == ValueType.FIELD) {
            value = field(name(valueStr.trim().toLowerCase()));
        }

        String op = ctx.operator().getText();
        return doRemainingParseJob(valueType, value, fieldName, op);
    }

    protected abstract T doRemainingParseJob(ValueType valueType, Object value, String fieldName, String op);
}
