package com.example.sysmanage.service.impl;

import com.example.sysmanage.dto.request.LoginDTO;
import com.example.sysmanage.dto.request.RegisterDTO;
import com.example.sysmanage.entity.SysUser;
import com.example.sysmanage.exception.BusinessException;
import com.example.sysmanage.mapper.SysUserMapper;
import com.example.sysmanage.utils.JwtUtils;
import com.example.sysmanage.security.UserDetailsImpl;
import com.example.sysmanage.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * 描述：
 * 创建日期：2025-02-22
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    @Transactional
    public SysUser register(RegisterDTO registerDTO) {
        // 检查用户名是否已存在
        if (userMapper.selectByUsername(registerDTO.getUsername()) != null) {
            throw new BusinessException("用户名已存在");
        }

        // 创建新用户
        SysUser user = new SysUser();
        user.setUsername(registerDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setEmail(registerDTO.getEmail());
        user.setPhone(registerDTO.getPhone());
        user.setStatus(1); // 1: 正常状态
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        user.setDeleted(0); // 0: 未删除

        // 保存用户
        userMapper.insert(user);
        
        // 返回用户信息（不包含密码）
        user.setPassword(null);
        return user;
    }

    @Override
    public String login(LoginDTO loginDTO) {
        // 进行身份认证
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword())
        );

        // 认证成功，设置Authentication到SecurityContext
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 更新最后登录时间
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        SysUser user = userDetails.getSysUser();
        user.setLastLoginTime(LocalDateTime.now());
        userMapper.updateById(user);

        // 生成JWT token
        return jwtUtils.generateToken(userDetails);
    }

    @Override
    public void logout() {
        // 清除SecurityContext
        SecurityContextHolder.clearContext();
    }

    @Override
    public SysUser getCurrentUser() {
        // 从SecurityContext获取当前用户信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new BusinessException("用户未登录");
        }

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        SysUser user = userDetails.getSysUser();
        
        // 重新查询最新的用户信息
        SysUser freshUser = userMapper.selectById(user.getId());
        if (freshUser == null) {
            throw new BusinessException("用户不存在");
        }
        
        // 不返回敏感信息
        freshUser.setPassword(null);
        return freshUser;
    }

    @Override
    public String refreshToken() {
        // 获取当前用户
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new BusinessException("用户未登录");
        }

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        // 生成新的token
        return jwtUtils.generateToken(userDetails);
    }
}
