package com.silentcloud.springrest.service.impl.query.jpa;

import cn.hutool.core.lang.Assert;
import com.github.wenhao.jpa.Specifications;
import com.silentcloud.springrest.service.api.query.IllegalQueryExprException.ErrorType;
import com.silentcloud.springrest.service.impl.query.AbstractQueryConditionExprParser;
import com.silentcloud.springrest.service.impl.query.antlr.gen.QueryExpressionParser;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Set;

public class JpaQueryConditionExprParser<T extends Persistable<?>> extends AbstractQueryConditionExprParser<Specification<T>> {

    public JpaQueryConditionExprParser(Set<String> legalFieldNames) {
        super("JPA", legalFieldNames);
        Assert.notEmpty(legalFieldNames);
    }

    @Override
    public Specification<T> visitExpr(QueryExpressionParser.ExprContext ctx) {
        if (!ctx.logical_op().isEmpty()) {
            Specification<?> left = visit(ctx.expr(0));
            Specification<?> right = visit(ctx.expr(1));
            switch (ctx.logical_op().get(0).getText()) {
                case "&&":
                    return Specifications.<T>and().predicate(left).predicate(right).build();
                case "||":
                    return Specifications.<T>or().predicate(left).predicate(right).build();
            }
        }

        if (ctx.start.getText().equals("(") && ctx.stop.getText().equals(")")) {
            return visit(ctx.expr(0));
        }

        return visitChildren(ctx);
    }

    @Override
    protected Specification<T> doRemainingParseJob(ValueType valueType, Object value, String fieldName, String op) {
        switch (op) {
            case "=":
                if (valueType == ValueType.LITERAL_NULL) {
                    return Specifications.<T>and().eq(fieldName).build();
                } else {
                    return Specifications.<T>and().eq(fieldName, value).build();
                }
            case ">":
                return Specifications.<T>and().gt(fieldName, (Comparable<?>) value).build();
            case ">=":
                return Specifications.<T>and().ge(fieldName, (Comparable<?>) value).build();
            case "<":
                return Specifications.<T>and().lt(fieldName, (Comparable<?>) value).build();
            case "<=":
                return Specifications.<T>and().le(fieldName, (Comparable<?>) value).build();
            case "!=":
                if (valueType == ValueType.LITERAL_NULL) {
                    return Specifications.<T>and().ne(fieldName).build();
                } else {
                    return Specifications.<T>and().ne(fieldName, value).build();
                }
            case "contains":
                return Specifications.<T>and().like(fieldName, "%" + value + "%").build();
            case "like":
                return Specifications.<T>and().like(fieldName, String.valueOf(value)).build();
            default:
                throw illegalQueryExprException(ErrorType.OPERATOR, op);
        }
    }
}
