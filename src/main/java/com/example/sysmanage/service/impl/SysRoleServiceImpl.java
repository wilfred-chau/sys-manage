package com.example.sysmanage.service.impl;

import com.example.sysmanage.mapper.SysRoleMapper;
import com.example.sysmanage.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper roleMapper;

    @Override
    public List<String> getRolesByUserId(Long userId) {
        return roleMapper.selectRoleNamesByUserId(userId);
    }
} 