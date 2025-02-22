package com.example.sysmanage.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 描述：
 * 创建日期：2025-02-22
 */
@Data
public class LoginDTO {

    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;
}
