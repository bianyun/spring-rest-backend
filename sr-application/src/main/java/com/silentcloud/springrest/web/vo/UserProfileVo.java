package com.silentcloud.springrest.web.vo;

import com.silentcloud.springrest.service.api.dto.sys.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Set;

@Data
@ApiModel("用户资料")
public class UserProfileVo {
    @ApiModelProperty(position = 1, value = "用户基本信息")
    private UserDto user;

    @ApiModelProperty(position = 2, value = "菜单权限集合")
    private Set<MenuDto> menuPerms;

    @ApiModelProperty(position = 3, value = "按钮权限集合")
    private Set<ButtonDto> buttonPerms;

    @ApiModelProperty(position = 4, value = "接口权限集合")
    private Set<ApiPermDto> apiPerms;

    @ApiModelProperty(position = 5, value = "角色集合")
    private Set<RoleDto> roles;

}
