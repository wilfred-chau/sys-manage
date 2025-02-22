package com.example.sysmanage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.sysmanage.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {
    List<String> selectRoleNamesByUserId(Long userId);
} 