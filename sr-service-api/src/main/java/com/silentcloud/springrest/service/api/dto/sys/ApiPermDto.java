package com.silentcloud.springrest.service.api.dto.sys;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.silentcloud.springrest.model.entity.sys.ApiPerm;
import com.silentcloud.springrest.service.api.dto.BaseDto;
import com.silentcloud.springrest.service.api.dto.Unique;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("接口权限")
public class ApiPermDto extends BaseDto<Long, ApiPerm> {

    @Unique
    @NotBlank
    private String name;

    @Unique
    @NotBlank
    private String value;

    @JsonBackReference("parent-reference")
    private ApiPermDto parent;

    private List<ApiPermDto> children = new ArrayList<>();

    public void addChild(ApiPermDto child) {
        children.add(child);
        child.setParent(this);
    }

    public void removeChild(ApiPermDto child) {
        children.remove(child);
        child.setParent(null);
    }
}
