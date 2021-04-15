package com.silentcloud.springrest.service.api.query.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 查询参数
 *
 * @author bianyun
 */
@Data
public class QueryParam implements Serializable {
    private static final long serialVersionUID = -1354736247036992385L;

    @ApiModelProperty(value = "查询条件表达式", position = 3)
    private String queryConditionExpr;

    @ApiModelProperty(value = "查询排序表达式", position = 4)
    private String querySortExpr;

}
