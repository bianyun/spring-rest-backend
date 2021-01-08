package com.silentcloud.springrest.service.api.dto.sys;

import com.silentcloud.springrest.model.entity.sys.Role;
import com.silentcloud.springrest.service.api.dto.BaseDto;
import com.silentcloud.springrest.service.api.dto.Unique;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("角色")
public class RoleDto extends BaseDto<Long, Role> {
    public static final String PREDEFINED_ROLE_SUPERADMIN = "SUPERADMIN";
    public static final RoleDto ROLE_SUPERADMIN;

    static {
        ROLE_SUPERADMIN = new RoleDto();
        ROLE_SUPERADMIN.setName("超级管理员");
        ROLE_SUPERADMIN.setValue(PREDEFINED_ROLE_SUPERADMIN);
    }

    @ApiModelProperty(position = 1, value = "角色名", example = "操作员", required = true)
    @Unique
    @NotBlank
    private String name;

    @ApiModelProperty(position = 2, value = "角色值", example = "Operator", required = true)
    @Unique
    @NotBlank
    private String value;

}
