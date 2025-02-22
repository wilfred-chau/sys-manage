package com.example.sysmanage.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("sys_user")
public class SysUser {

    private Long id;                           // 用户ID
    private String username;                   // 用户名
    private String password;                   // 密码
    private String email;                      // 邮箱
    private String phone;                      // 电话
    private Long departmentId;                 // 部门ID
    private String avatar;                     // 头像
    private LocalDateTime lastLoginTime;       // 最后登录时间
    private Integer isAdmin;                   // 是否管理员
    private Integer status;                    // 状态
    private LocalDateTime createTime;          // 创建时间
    private LocalDateTime updateTime;          // 更新时间
    private Integer deleted;                   // 删除标志
}
