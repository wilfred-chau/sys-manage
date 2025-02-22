package com.example.sysmanage.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("sys_user_menu_permission")
public class SysUserMenuPermission {

    private Long userId;                       // 用户ID
    private Long menuId;                       // 菜单ID
    private LocalDateTime createTime;          // 创建时间
    private Integer deleted;                   // 删除标志
}
