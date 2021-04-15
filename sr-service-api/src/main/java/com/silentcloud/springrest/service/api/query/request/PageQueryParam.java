package com.silentcloud.springrest.service.api.query.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 分页查询参数
 * 
 * @author bianyun
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PageQueryParam extends QueryParam {

    private static final int DEFAULT_PAGE_NUMBER = 1;
    private static final int DEFAULT_PAGE_SIZE = 10;

    @ApiModelProperty(value = "页码", example = "1", position = 1)
    private Integer pageNumber;

    @ApiModelProperty(value = "页大小", example = "10", position = 2)
    private Integer pageSize;

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return isInvalid(pageNumber) ? DEFAULT_PAGE_NUMBER : pageNumber;
    }

    public int getPageSize() {
        return isInvalid(pageSize) ? DEFAULT_PAGE_SIZE : pageSize;
    }

    private static boolean isInvalid(Integer value) {
        return value == null || value < 1 || value >= Integer.MAX_VALUE;
    }

    @ApiModelProperty(hidden = true)
    public int getOffset() {
        return (getPageNumber() - 1) * getPageSize();
    }

}
