package com.example.sysmanage.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("sys_role")
public class SysRole {

    private Long id;                           // 角色ID
    private String roleName;                   // 角色名称
    private String description;                // 角色描述
    private Integer level;                     // 角色等级
    private Integer isSystemRole;              // 是否是系统角色
    private Integer status;                    // 状态
    private LocalDateTime createTime;          // 创建时间
    private LocalDateTime updateTime;          // 更新时间
    private Integer deleted;                   // 删除标志
}
