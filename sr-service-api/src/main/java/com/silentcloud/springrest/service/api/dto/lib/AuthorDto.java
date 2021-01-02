package com.silentcloud.springrest.service.api.dto.lib;

import com.silentcloud.springrest.model.entity.lib.Author;
import com.silentcloud.springrest.model.enums.CountryCode;
import com.silentcloud.springrest.model.enums.Gender;
import com.silentcloud.springrest.service.api.dto.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("作者")
public class AuthorDto extends BaseDto<Long, Author> {

    @NotBlank
    @ApiModelProperty(position = 1, value = "姓名", example = "雨果")
    private String name;

    @ApiModelProperty(position = 2, value = "性别")
    private Gender gender = Gender.UNSPECIFIED;

    @ApiModelProperty(position = 3, value = "国家代码")
    private CountryCode countryCode = CountryCode.CN;

}
