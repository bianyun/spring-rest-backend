package com.silentcloud.springrest.service.api.dto.sys;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.silentcloud.springrest.model.entity.sys.ApiPerm;
import com.silentcloud.springrest.service.api.dto.BaseDto;
import com.silentcloud.springrest.service.api.dto.PermLevel;
import com.silentcloud.springrest.service.api.dto.Unique;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("接口权限")
public class ApiPermDto extends BaseDto<Long, ApiPerm> {

    @Unique
    @NotBlank
    @ApiModelProperty(position = 1, value = "权限名", example = "添加用户", required = true)
    private String name;

    @Unique
    @NotBlank
    @ApiModelProperty(position = 2, value = "权限值", example = "api:sys:user:add", required = true)
    private String value;

    @ApiModelProperty(hidden = true)
    private PermLevel permLevel;

    @JsonBackReference("parent-reference")
    @ApiModelProperty(hidden = true)
    private ApiPermDto parent;

    @ApiModelProperty(position = 3, value = "子权限")
    private List<ApiPermDto> children;



}
