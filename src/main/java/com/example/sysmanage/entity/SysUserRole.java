package com.example.sysmanage.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("sys_user_role")
public class SysUserRole {

    private Long userId;                       // 用户ID
    private Long roleId;                       // 角色ID
    private LocalDateTime createTime;          // 创建时间
    private Integer deleted;                   // 删除标志
}
