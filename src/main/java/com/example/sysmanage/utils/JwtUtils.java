package com.example.sysmanage.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述：JWT工具类
 * 创建日期：2025-02-22
 */
@Component
public class JwtUtils {

    // JWT密钥
    @Value("${jwt.secret}")
    private String secret;

    // Token有效期（24小时）
    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     * 生成Token
     * @param userDetails 用户信息
     * @return Token字符串
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", userDetails.getUsername());
        claims.put("created", new Date());
        return generateToken(claims);
    }

    public String getUsernameFromToken(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            return claims.get("username", String.class);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 验证token是否有效
     * @param token
     * @param userDetails
     * @return
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        String username = getUsernameFromToken(token);
        return username != null &&
                username.equals(userDetails.getUsername()) &&
                !isTokenExpired(token);
    }

    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    private Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    private boolean isTokenExpired(String token) {
        Date expiration = getClaimsFromToken(token).getExpiration();
        return expiration.before(new Date());
    }


}
