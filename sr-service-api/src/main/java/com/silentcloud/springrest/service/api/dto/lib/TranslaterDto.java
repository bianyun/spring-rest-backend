package com.silentcloud.springrest.service.api.dto.lib;

import com.silentcloud.springrest.model.entity.lib.Translater;
import com.silentcloud.springrest.model.enums.CountryCode;
import com.silentcloud.springrest.service.api.dto.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("译者")
public class TranslaterDto extends BaseDto<Long, Translater> {

    @NotBlank
    @ApiModelProperty(position = 1, value = "姓名", example = "傅雷", required = true)
    private String name;

    @ApiModelProperty(position = 2, value = "国家代码", allowableValues = "CN, US, ...")
    private CountryCode countryCode;

}
