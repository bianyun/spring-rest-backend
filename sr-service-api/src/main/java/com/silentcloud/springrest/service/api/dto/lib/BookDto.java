package com.silentcloud.springrest.service.api.dto.lib;

import com.silentcloud.springrest.model.entity.lib.Book;
import com.silentcloud.springrest.service.api.dto.BaseDto;
import com.silentcloud.springrest.service.api.dto.Unique;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;


@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("图书")
public class BookDto extends BaseDto<Long, Book> {

    @NotBlank
    @Unique
    @ApiModelProperty(position = 1, value = "ISBN", example = "978-0-13-469288-3", required = true)
    private String isbn;

    @NotBlank
    @Unique(scope = "publisherId")
    @ApiModelProperty(position = 2, value = "名称", example = "动物农场", required = true)
    private String title;

    @ApiModelProperty(position = 3, value = "字数", example = "500000")
    private Long wordCount;

    @ApiModelProperty(position = 4, value = "价格", example = "6890")
    private Long unitPrice;

    @ApiModelProperty(position = 5, value = "译者", example = "张三，李四")
    private String translaters;

    @NotNull
    @ApiModelProperty(position = 6, value = "出版社ID", required = true)
    private Long publisherId;

    @NotEmpty
    @ApiModelProperty(position = 7, value = "作者ID集合", required = true)
    private Set<Long> authorIds;

}
