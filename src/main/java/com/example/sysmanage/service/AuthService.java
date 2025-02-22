package com.example.sysmanage.service;

import com.example.sysmanage.dto.request.LoginDTO;
import com.example.sysmanage.dto.request.RegisterDTO;
import com.example.sysmanage.entity.SysUser;

public interface AuthService {
    /**
     * 用户注册
     * @param registerDTO 注册信息
     * @return 注册成功的用户信息
     */
    SysUser register(RegisterDTO registerDTO);

    /**
     * 用户登录
     * @param loginDTO 登录信息
     * @return JWT token
     */
    String login(LoginDTO loginDTO);

    /**
     * 退出登录
     */
    void logout();

    /**
     * 获取当前登录用户信息
     * @return 当前用户信息
     */
    SysUser getCurrentUser();

    /**
     * 刷新Token
     * @return 新的token
     */
    String refreshToken();
}
