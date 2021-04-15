package com.silentcloud.springrest.service.api.dto.lib;

import com.silentcloud.springrest.model.entity.lib.Book;
import com.silentcloud.springrest.service.api.dto.BaseDto;
import com.silentcloud.springrest.service.api.dto.Unique;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import java.time.YearMonth;
import java.util.List;

import static com.silentcloud.springrest.service.api.dto.ValidationGroups.*;

/**
 * 图书 DTO
 *
 * @author bianyun
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("图书")
public class BookDto extends BaseDto<Long, Book> {

    @NotBlank
    @Unique
    @ApiModelProperty(position = 1, value = "ISBN", example = "978-0-13-469288-3", required = true)
    private String isbn;

    @NotBlank
    @Unique(scope = "publisher")
    @ApiModelProperty(position = 2, value = "名称", example = "动物农场", required = true)
    private String title;

    @ApiModelProperty(position = 3, value = "字数", example = "500000")
    private Long wordCount;

    @ApiModelProperty(position = 4, value = "价格", example = "6890")
    private Long unitPrice;

    @ApiModelProperty(position = 5, value = "译者", example = "张三，李四")
    private String translaters;

    @ApiModelProperty(position = 6, value = "出版时间", example = "2021-01")
    private YearMonth publishedOn;

    @NotNull
    @ApiModelProperty(position = 7, value = "出版社")
    @Valid
    @ConvertGroup.List({
            @ConvertGroup(from = Create.class, to = Reference.class),
            @ConvertGroup(from = Update.class, to = Reference.class),
            @ConvertGroup(to = Reference.class),
    })
    private PublisherDto publisher;

    @NotEmpty
    @Valid
    @ApiModelProperty(position = 8, value = "作者列表")
    @ConvertGroup.List({
            @ConvertGroup(from = Create.class, to = Reference.class),
            @ConvertGroup(from = Update.class, to = Reference.class),
            @ConvertGroup(to = Reference.class),
    })
    private List<AuthorDto> authors;
}
