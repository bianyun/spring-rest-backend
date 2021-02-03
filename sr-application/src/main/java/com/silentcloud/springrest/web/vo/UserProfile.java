package com.silentcloud.springrest.web.vo;

import com.silentcloud.springrest.service.api.dto.sys.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@ApiModel("用户资料")
public class UserProfile {
    @ApiModelProperty(position = 1, value = "用户基本信息")
    private UserDto user;

    @ApiModelProperty(position = 2, value = "角色列表")
    private List<RoleDto> roles;

    @ApiModelProperty(position = 3, value = "菜单权限集合")
    private Set<MenuDto> menuPerms;

    @ApiModelProperty(position = 4, value = "按钮权限集合")
    private Set<ButtonDto> btnPerms;

    @ApiModelProperty(position = 5, value = "接口权限集合")
    private Set<ApiPermDto> apiPerms;

    @ApiModelProperty(position = 6, value = "演示模式是否开启")
    private boolean demoModeEnabled;

    @ApiModelProperty(position = 7, value = "演示模式内置演示用户列表")
    private List<String> demoPreservedUsers;

}
