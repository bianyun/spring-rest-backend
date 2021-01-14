package com.silentcloud.springrest.web.vo;

import com.silentcloud.springrest.service.api.dto.sys.ApiPermDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel("接口权限元数据")
@Data
public class ApiPermMetaData {

    @ApiModelProperty(position = 1, value = "未同步的接口权限值列表")
    private List<String> unsyncedApiPermValues;

    @ApiModelProperty(position = 2, value = "接口权限数据")
    private List<ApiPermDto> apiPermTreeData;

}
