package com.silentcloud.springrest.service.impl.query.flat;

import cn.hutool.core.lang.Assert;
import com.silentcloud.springrest.service.api.query.IllegalQueryExprException.ErrorType;
import com.silentcloud.springrest.service.impl.query.AbstractQueryConditionExprParser;
import com.silentcloud.springrest.service.impl.query.antlr.gen.QueryExpressionParser;
import org.jooq.Condition;
import org.jooq.Field;

import java.util.Set;

import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.name;

public class FlatQueryConditionExprParser extends AbstractQueryConditionExprParser<Condition> {

    public FlatQueryConditionExprParser(Set<String> legalFieldNames) {
        super("扁平", legalFieldNames);
        Assert.notEmpty(legalFieldNames);
    }

    @Override
    public Condition visitExpr(QueryExpressionParser.ExprContext ctx) {
        if (!ctx.logical_op().isEmpty()) {
            Condition left = visit(ctx.expr(0));
            switch (ctx.logical_op().get(0).getText()) {
                case "&&":
                    return left.and(visit(ctx.expr(1)));
                case "||":
                    return left.or(visit(ctx.expr(1)));
            }
        }

        if (ctx.start.getText().equals("(") && ctx.stop.getText().equals(")")) {
            return visit(ctx.expr(0));
        }

        return visitChildren(ctx);
    }

    @Override
    protected Condition doRemainingParseJob(ValueType valueType, Object value, String fieldName, String op) {
        String valueStr = value.toString();
        Field<Object> field = field(name(fieldName));
        switch (op) {
            case "=":
                if (valueType == ValueType.LITERAL_NULL) {
                    return field.isNull();
                } else {
                    return field.eq(value);
                }
            case ">":
                return field.gt(value);
            case ">=":
                return field.ge(value);
            case "<":
                return field.lt(value);
            case "<=":
                return field.le(value);
            case "!=":
                if (valueType == ValueType.LITERAL_NULL) {
                    return field.isNotNull();
                } else {
                    return field.ne(value);
                }
            case "contains":
                if (valueType == ValueType.FIELD) {
                    throw illegalQueryExprException("值类型为字段时不支持 contains 操作符: " + valueStr);
                } else {
                    return field.contains(valueStr);
                }
            case "like":
                if (valueType == ValueType.FIELD) {
                    throw illegalQueryExprException("值类型为字段时不支持 like 操作符" + valueStr);
                } else {
                    return field.like(valueStr);
                }
            default:
                throw illegalQueryExprException(ErrorType.OPERATOR, op);
        }
    }
}
