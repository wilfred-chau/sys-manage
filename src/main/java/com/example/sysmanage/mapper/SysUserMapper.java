package com.example.sysmanage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.sysmanage.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
    SysUser selectByUsername(String username);
} 