package com.silentcloud.springrest.service.api.query.response;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.silentcloud.springrest.service.api.query.request.PageQueryParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Value;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 分页对象
 *
 * @author bianyun
 */
@Value(staticConstructor = "of")
public class PageInfo<E> implements Serializable {
    private static final long serialVersionUID = 5066619052922125191L;

    @ApiModelProperty("分页数据")
    List<E> list;

    @ApiModelProperty(hidden = true)
    @JsonBackReference
    PageQueryParam pageQueryParam;

    @ApiModelProperty(value = "总数据量", example = "45")
    long totalElements;

    @ApiModelProperty(value = "页码", example = "1")
    public int getPageNumber() {
        return pageQueryParam.getPageNumber();
    }

    @ApiModelProperty(value = "页大小", example = "10")
    public int getPageSize() {
        return pageQueryParam.getPageSize();
    }

    @ApiModelProperty(value = "总页数", example = "5")
    public int getTotalPages() {
        return (int) Math.ceil((double) getTotalElements() / (double) getPageSize());
    }

    public static <E, T> PageInfo<T> fromPage(Page<E> page, PageQueryParam pageQueryParam,
                                              Function<? super E, ? extends T> converter) {
        return PageInfo.of(page.getContent(), pageQueryParam, page.getTotalElements()).map(converter);
    }

    public <T> PageInfo<T> map(Function<? super E, ? extends T> converter) {
        List<T> convertedContent = getConvertedContent(converter);
        return new PageInfo<>(convertedContent, getPageQueryParam(), getTotalElements());
    }

    private <T> List<T> getConvertedContent(Function<? super E, ? extends T> converter) {
        return this.list.stream().map(converter).collect(Collectors.toList());
    }
}
