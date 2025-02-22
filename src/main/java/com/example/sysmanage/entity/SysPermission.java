package com.example.sysmanage.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("sys_permission")
public class SysPermission {

    private Long id;                           // 权限ID
    private String permissionName;             // 权限名称
    private String permissionCode;             // 权限代码
    private Long parentId;                     // 父权限ID
    private String description;                // 权限描述
    private LocalDateTime createTime;          // 创建时间
    private LocalDateTime updateTime;          // 更新时间
    private Integer deleted;                   // 删除标志
}
