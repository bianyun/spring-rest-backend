package com.silentcloud.springrest.web.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
public class LinkApiPermValuesToMenuVo {

    @ApiModelProperty(value = "菜单权限值", position = 1, example = "menu:sys:user", required = true)
    @NotBlank
    private String menuPermValue;

    @ApiModelProperty(value = "菜单关联的接口权限值集合", position = 2, example = "api:sys:user:add, api:sys:user:edit", required = true)
    @NotNull
    private Set<String> apiPermValues;

}
