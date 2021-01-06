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
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
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

    @ApiModelProperty(position = 4, value = "子菜单")
    private List<MenuDto> children = new ArrayList<>();

    public void addChild(MenuDto child) {
        children.add(child);
        child.setParent(this);
    }

    public void removeChild(MenuDto child) {
        children.remove(child);
        child.setParent(null);
    }
}
