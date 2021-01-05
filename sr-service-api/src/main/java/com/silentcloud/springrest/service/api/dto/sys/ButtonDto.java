package com.silentcloud.springrest.service.api.dto.sys;

import com.silentcloud.springrest.model.entity.sys.Button;
import com.silentcloud.springrest.service.api.dto.BaseDto;
import com.silentcloud.springrest.service.api.dto.Unique;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("按钮")
public class ButtonDto extends BaseDto<Long, Button> {

    @NotBlank
    private String name;

    @Unique
    @NotBlank
    private String value;

    @NotNull
    private Long parentMenuId;

    private String parentMenuName;

}
