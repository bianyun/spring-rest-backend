package com.silentcloud.springrest.web.vo;

import lombok.Data;

@Data
public class UpdatePasswordFormData {
    private String oldPassword;
    private String newPassword;
}
