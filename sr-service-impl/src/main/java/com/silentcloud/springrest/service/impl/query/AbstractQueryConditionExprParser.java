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
import com.silentcloud.springrest.util.MiscUtil;
import com.silentcloud.springrest.util.StrUtils;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;

import java.util.Set;

import static com.silentcloud.springrest.service.impl.query.AbstractQueryConditionExprParser.ValueType.LITERAL_NULL;
import static com.silentcloud.springrest.util.StrUtils.*;
import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.name;

/**
 * 查询表达式解析器抽象父类
 *
 * @param <T> 查询条件对象类型
 * @author bianyun
 */
public abstract class AbstractQueryConditionExprParser<T> extends QueryExpressionBaseVisitor<T>
        implements QueryConditionExprParser<T> {
    public static final String MISSING_STRING = "<missing STRING>";
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

    private static boolean isPartiallyWrappedBySingleQuote(String valueStr) {
        return (valueStr.startsWith(SINGLE_QUOTE) && !valueStr.endsWith(SINGLE_QUOTE)) ||
                (!valueStr.startsWith(SINGLE_QUOTE) && valueStr.endsWith(SINGLE_QUOTE));
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
        String queryExpression = ctx.getText().replace(MISSING_STRING, StrUtils.EMPTY);

        String valueStr = ctx.value().getText().trim();
        if (StrUtil.equals(valueStr, MISSING_STRING)) {
            throw illegalQueryExprException(queryExpression);
        }
        ValueType valueType;

        if (isPartiallyWrappedBySingleQuote(valueStr)) {
            throw illegalQueryExprException(queryExpression);
        } else if (StrUtil.isWrap(valueStr, SINGLE_QUOTE)) {
            valueStr = valueStr.substring(1, valueStr.length() - 1).trim();
            valueType = ValueType.STRING;
        } else {
            if (NumberUtil.isInteger(valueStr)) {
                valueType = ValueType.INTEGER;
            } else if (NumberUtil.isLong(valueStr)) {
                valueType = ValueType.LONG;
            } else if (NumberUtil.isDouble(valueStr)) {
                valueType = ValueType.DOUBLE;
            } else if (StrUtil.equalsAnyIgnoreCase(valueStr, TRUE, FALSE)) {
                valueType = ValueType.BOOLEAN;
            } else if (NULL.equalsIgnoreCase(valueStr)) {
                valueType = LITERAL_NULL;
            } else if (valueStr.contains(JooqUtil.DELIMETER_BETWEEN_TABLE_AND_COLUMN)
                    && this instanceof FlatQueryConditionExprParser) {
                valueType = ValueType.FIELD;
                checkFieldNameValidity(valueStr);
            } else {
                throw illegalQueryExprException(queryExpression);
            }
        }

        Object value;
        switch (valueType) {
            case STRING:
                value = valueStr;
                break;
            case INTEGER:
                value = Integer.valueOf(valueStr);
                break;
            case LONG:
                value = Long.valueOf(valueStr);
                break;
            case DOUBLE:
                value = Double.valueOf(valueStr);
                break;
            case BOOLEAN:
                value = Boolean.valueOf(valueStr);
                break;
            case FIELD:
                value = field(name(valueStr.trim().toLowerCase()));
                break;
            case LITERAL_NULL:
                value = LITERAL_NULL.name();
                break;
            default:
                return MiscUtil.unreachableButCompilerNeedsThis();
        }

        String op = ctx.operator().getText();
        return doRemainingParseJob(valueType, value, fieldName, op);
    }

    /**
     * 完成剩余的解析操作
     *
     * @param valueType 值类型
     * @param value     值
     * @param fieldName 字段名
     * @param op        操作符
     * @return 查询条件
     */
    protected abstract T doRemainingParseJob(ValueType valueType, Object value, String fieldName, String op);

    /**
     * 值类型
     */
    protected enum ValueType {
        /**
         * 字符串
         */
        STRING,

        /**
         * 整型
         */
        INTEGER,

        /**
         * 长整型
         */
        LONG,

        /**
         * 双精度浮点型
         */
        DOUBLE,

        /**
         * 布尔型
         */
        BOOLEAN,

        /**
         * 字面常量NULL
         */
        LITERAL_NULL,

        /**
         * 字段名
         */
        FIELD
    }
}
