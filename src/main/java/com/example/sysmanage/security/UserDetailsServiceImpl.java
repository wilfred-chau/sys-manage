package com.example.sysmanage.security;

import com.example.sysmanage.entity.SysUser;
import com.example.sysmanage.mapper.SysUserMapper;
import com.example.sysmanage.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private SysRoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 查询用户
        SysUser user = userMapper.selectByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }

        // 查询用户角色
        List<String> roles = roleService.getRolesByUserId(user.getId());

        return UserDetailsImpl.build(user, roles);
    }
} 