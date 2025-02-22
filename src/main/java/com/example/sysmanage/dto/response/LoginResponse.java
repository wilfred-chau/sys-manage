package com.example.sysmanage.dto.response;

import com.example.sysmanage.security.UserDetailsImpl;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private UserDetailsImpl userInfo;
} 