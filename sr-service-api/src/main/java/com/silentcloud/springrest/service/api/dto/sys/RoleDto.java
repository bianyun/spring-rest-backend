package com.silentcloud.springrest.service.api.dto.sys;

import com.silentcloud.springrest.model.entity.sys.Role;
import com.silentcloud.springrest.service.api.dto.BaseDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("角色")
public class RoleDto extends BaseDto<Long, Role> {

    private String name;

    private String value;

}
