package com.silentcloud.springrest.web.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@ApiModel("登录信息")
@Data
public class LoginFormData {

    @ApiModelProperty(value = "用户名", position = 1, example = "username", required = true)
    @NotBlank
    private String username;

    @ApiModelProperty(value = "密码", position = 2, example = "password", required = true)
    @NotBlank
    private String password;

}
