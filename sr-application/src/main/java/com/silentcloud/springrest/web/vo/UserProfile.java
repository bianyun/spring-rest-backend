package com.silentcloud.springrest.web.vo;

import com.silentcloud.springrest.service.api.dto.sys.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("用户资料")
public class UserProfile {
    @ApiModelProperty(position = 1, value = "用户基本信息")
    private UserDto user;

    @ApiModelProperty(position = 2, value = "角色列表")
    private List<RoleDto> roles;

    @ApiModelProperty(position = 3, value = "菜单权限列表")
    private List<MenuDto> menuPerms;

    @ApiModelProperty(position = 4, value = "按钮权限列表")
    private List<ButtonDto> btnPerms;

    @ApiModelProperty(position = 5, value = "接口权限列表")
    private List<ApiPermDto> apiPerms;

}
