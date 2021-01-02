package com.silentcloud.springrest.service.api.dto.lib;

import com.silentcloud.springrest.model.entity.lib.Translater;
import com.silentcloud.springrest.model.enums.CountryCode;
import com.silentcloud.springrest.service.api.dto.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("译者")
public class TranslaterDto extends BaseDto<Long, Translater> {

    @NotBlank
    @ApiModelProperty(position = 1, value = "姓名", example = "傅雷")
    private String name;

    @ApiModelProperty(position = 2, value = "国家代码")
    private CountryCode countryCode = CountryCode.CN;

}
