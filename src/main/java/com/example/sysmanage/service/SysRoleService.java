package com.example.sysmanage.service;

import java.util.List;

public interface SysRoleService {
    List<String> getRolesByUserId(Long userId);
} 