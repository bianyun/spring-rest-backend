package com.silentcloud.springrest.web.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 修改密码表单数据VO
 *
 * @author bianyun
 */
@Data
public class UpdatePasswordFormData {

    @ApiModelProperty(position = 1, value = "原密码")
    @NotBlank
    private String oldPassword;

    @ApiModelProperty(position = 2, value = "新密码")
    @NotBlank
    private String newPassword;
}
