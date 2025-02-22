package com.example.sysmanage.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("sys_role_permission")
public class SysRolePermission {

    private Long roleId;                       // 角色ID
    private Long permissionId;                 // 权限ID
    private LocalDateTime createTime;          // 创建时间
    private Integer deleted;                   // 删除标志
}
