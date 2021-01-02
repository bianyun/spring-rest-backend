package com.silentcloud.springrest.service.api.dto.sys;

import com.silentcloud.springrest.model.entity.sys.Button;
import com.silentcloud.springrest.service.api.dto.BaseDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("按钮")
public class ButtonDto extends BaseDto<Long, Button> {

    private String name;

    private String value;

    private Long parentMenuId;

    private String parentMenuName;

}
