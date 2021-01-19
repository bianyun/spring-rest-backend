package com.silentcloud.springrest.service.api.dto.sys;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.silentcloud.springrest.model.entity.sys.Menu;
import com.silentcloud.springrest.service.api.dto.BaseDto;
import com.silentcloud.springrest.service.api.dto.Unique;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("菜单")
public class MenuDto extends BaseDto<Long, Menu> {

    @ApiModelProperty(position = 1, value = "菜单名", example = "用户管理", required = true)
    @Unique
    @NotBlank
    private String name;

    @ApiModelProperty(position = 2, value = "权限值", example = "menu:sys:user", required = true)
    @Unique
    @NotBlank
    private String value;

    @JsonBackReference("parent-reference")
    @ApiModelProperty(hidden = true)
    private MenuDto parent;

    @ApiModelProperty(position = 3, value = "父亲菜单名")
    private String parentMenuName;

    @ApiModelProperty(position = 4, value = "父亲菜单权限值")
    private String parentMenuValue;

    @JsonBackReference("children-reference")
    @ApiModelProperty(position = 5, value = "子菜单")
    private List<MenuDto> children;

    @ApiModelProperty(position = 6, value = "关联的接口权限值集合")
    private Set<String> apiPermValues;

}
