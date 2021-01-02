package com.silentcloud.springrest.service.api.dto.lib;

import com.silentcloud.springrest.model.entity.lib.Publisher;
import com.silentcloud.springrest.model.enums.CountryCode;
import com.silentcloud.springrest.service.api.dto.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("出版社")
public class PublisherDto extends BaseDto<Long, Publisher> {

    @NotBlank
    @ApiModelProperty(position = 1, value = "名称", example = "科学出版社")
    private String name;

    @ApiModelProperty(position = 2, value = "地址", example = "梦想大道598号")
    private String address;

    @ApiModelProperty(position = 3, value = "邮政编码", example = "123456")
    private String zipCode;

    @ApiModelProperty(position = 4, value = "国家代码")
    private CountryCode countryCode = CountryCode.CN;

}
