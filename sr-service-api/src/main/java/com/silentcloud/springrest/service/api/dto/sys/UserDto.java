package com.silentcloud.springrest.service.api.dto.sys;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.silentcloud.springrest.model.entity.sys.User;
import com.silentcloud.springrest.model.enums.Gender;
import com.silentcloud.springrest.service.api.dto.BaseDto;
import com.silentcloud.springrest.service.api.dto.Unique;
import com.silentcloud.springrest.service.api.dto.ValidationGroups.Create;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("用户")
public class UserDto extends BaseDto<Long, User> {
    public static final String DEFAULT_PASSWORD = "111111";
    public static final String PREDEFINED_USER_SUPERADMIN = "superadmin";

    @Unique
    @NotBlank
    @Size(min = 2, max = 18, message = "长度必须为{min}到{max}个字符")
    @ApiModelProperty(position = 1, value = "用户名", example = "user", required = true)
    private String username;

    @JsonBackReference("password")
    @NotBlank(groups = Create.class)
    @ApiModelProperty(position = 2, value = "密码", example = "password", required = true)
    private String password;

    @ApiModelProperty(position = 3, value = "昵称", example = "小李飞刀")
    private String nickname;

    @ApiModelProperty(position = 4, value = "姓名", example = "李寻欢")
    private String realname;

    @Unique
    @Email
    @ApiModelProperty(position = 5, value = "Email", example = "user@qq.com")
    private String email;

    @Unique
    @ApiModelProperty(position = 6, value = "手机号", example = "13812345678")
    private String mobile;

    @ApiModelProperty(position = 7, value = "性别")
    private Gender gender;

    @ApiModelProperty(position = 8, value = "头像图片地址")
    private String pictureUrl;

    @ApiModelProperty(position = 9, value = "是否启用", example = "true")
    private boolean active = true;

}
