package com.silentcloud.springrest.web.vo;

import com.silentcloud.springrest.service.api.dto.sys.ButtonDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@ApiModel("菜单权限元数据")
@Data
public class MenuPermMetaData {

    @ApiModelProperty(position = 1, value = "未同步的菜单权限值列表")
    private List<String> unsyncedMenuPermValues;

    @ApiModelProperty(position = 2, value = "菜单权限值到按钮权限列表的MAP")
    private Map<String, List<ButtonDto>> menuToButtonPermsMap;

}
