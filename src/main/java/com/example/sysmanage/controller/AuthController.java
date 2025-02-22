package com.example.sysmanage.controller;

import com.example.sysmanage.common.response.Result;
import com.example.sysmanage.dto.request.LoginDTO;
import com.example.sysmanage.dto.request.RegisterDTO;
import com.example.sysmanage.entity.SysUser;
import com.example.sysmanage.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述：
 * 创建日期：2025-02-22
 */
@Api(tags = "认证接口")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
    
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public Result<Map<String, Object>> register(@RequestBody RegisterDTO registerDTO) {
        SysUser user = authService.register(registerDTO);
        
        Map<String, Object> data = new HashMap<>();
        data.put("username", user.getUsername());
        data.put("email", user.getEmail());
        data.put("createTime", user.getCreateTime().format(DATE_FORMATTER));
        
        return Result.success("注册成功", data);
    }

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public Result<Map<String, Object>> login(@RequestBody LoginDTO loginDTO) {
        String token = authService.login(loginDTO);
        SysUser user = authService.getCurrentUser();
        
        Map<String, Object> data = new HashMap<>();
        data.put("token", token);
        data.put("username", user.getUsername());
        data.put("email", user.getEmail());
        data.put("isAdmin", user.getIsAdmin());
        data.put("lastLoginTime", user.getLastLoginTime() != null ? 
                user.getLastLoginTime().format(DATE_FORMATTER) : null);
        
        return Result.success("登录成功", data);
    }

    @ApiOperation("退出登录")
    @PostMapping("/logout")
    public Result<Object> logout() {
        authService.logout();
        return Result.success("退出成功");
    }

    @ApiOperation("获取当前用户信息")
    @GetMapping("/info")
    public Result<Map<String, Object>> getUserInfo() {
        SysUser user = authService.getCurrentUser();
        
        Map<String, Object> data = new HashMap<>();
        data.put("id", user.getId());
        data.put("username", user.getUsername());
        data.put("email", user.getEmail());
        data.put("phone", user.getPhone());
        data.put("avatar", user.getAvatar());
        data.put("departmentId", user.getDepartmentId());
        data.put("isAdmin", user.getIsAdmin());
        data.put("status", user.getStatus());
        data.put("lastLoginTime", user.getLastLoginTime() != null ? 
                user.getLastLoginTime().format(DATE_FORMATTER) : null);
        
        return Result.success(data);
    }

    @ApiOperation("刷新Token")
    @PostMapping("/refresh-token")
    public Result<Map<String, Object>> refreshToken() {
        String newToken = authService.refreshToken();
        
        Map<String, Object> data = new HashMap<>();
        data.put("token", newToken);
        
        return Result.success("Token刷新成功", data);
    }
}
