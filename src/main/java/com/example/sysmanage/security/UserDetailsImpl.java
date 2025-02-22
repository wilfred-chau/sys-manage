package com.example.sysmanage.security;

import com.example.sysmanage.entity.SysUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 描述：
 * 创建日期：2025-02-22
 */
@Data
public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String username;
    @JsonIgnore
    private String password;
    private String email;
    private String phone;
    private Integer status;
    private Collection<? extends GrantedAuthority> authorities;

    // 保存完整的用户信息
    private SysUser sysUser;

    public UserDetailsImpl(SysUser user, List<String> roles) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.status = user.getStatus();
        this.sysUser = user;
        
        // 转换角色为权限
        this.authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());
    }

    public static UserDetailsImpl build(SysUser user, List<String> roles) {
        return new UserDetailsImpl(user, roles);
    }

    // 获取完整的用户信息
    public SysUser getSysUser() {
        return this.sysUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return status == 1;
    }
}
