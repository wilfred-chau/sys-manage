package com.example.sysmanage.service.impl;

import com.example.sysmanage.entity.SysUserLoginLog;
import com.example.sysmanage.mapper.SysUserLoginLogMapper;
import com.example.sysmanage.service.SysUserLoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserLoginLogServiceImpl implements SysUserLoginLogService {

    @Autowired
    private SysUserLoginLogMapper loginLogMapper;

    @Override
    public void save(SysUserLoginLog loginLog) {
        loginLogMapper.insert(loginLog);
    }
} 