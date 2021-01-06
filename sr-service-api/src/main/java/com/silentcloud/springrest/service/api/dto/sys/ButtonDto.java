package com.silentcloud.springrest.service.api.dto.sys;

import com.silentcloud.springrest.model.entity.sys.Button;
import com.silentcloud.springrest.service.api.dto.BaseDto;
import com.silentcloud.springrest.service.api.dto.Unique;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("按钮权限")
public class ButtonDto extends BaseDto<Long, Button> {

    @ApiModelProperty(position = 1, value = "权限名", example = "添加用户", required = true)
    @NotBlank
    private String name;

    @ApiModelProperty(position = 2, value = "权限值", example = "button:sys:user:add", required = true)
    @Unique
    @NotBlank
    private String value;

    @ApiModelProperty(position = 3, value = "父亲菜单ID", example = "1", required = true)
    @NotNull
    private Long parentMenuId;

    @ApiModelProperty(position = 4, value = "父亲菜单名", example = "用户管理")
    private String parentMenuName;

}
