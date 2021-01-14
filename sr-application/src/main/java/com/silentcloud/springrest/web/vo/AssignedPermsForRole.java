package com.silentcloud.springrest.web.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
public class AssignedPermsForRole {

    @ApiModelProperty(position = 1, value = "角色分配的菜单权限值列表")
    @NotNull
    private Set<String> menuPermValues;

    @ApiModelProperty(position = 2, value = "角色分配的按钮权限值列表")
    @NotNull
    private Set<String> btnPermValues;
}
