package com.example.sysmanage.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("sys_user_login_log")
public class SysUserLoginLog {

    private Long id;                           // 日志ID
    private Long userId;                       // 用户ID
    private LocalDateTime loginTime;           // 登录时间
    private String ip;                         // 登录IP
    private String userAgent;                  // 用户代理
    private LocalDateTime createTime;          // 创建时间
}
