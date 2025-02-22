package com.example.sysmanage.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("sys_department")
public class SysDepartment {

    private Long id;                           // 部门ID
    private String departmentName;             // 部门名称
    private Long parentId;                     // 父部门ID
    private String description;                // 部门描述
    private LocalDateTime createTime;          // 创建时间
    private LocalDateTime updateTime;          // 更新时间
    private Integer deleted;                   // 删除标志
}
