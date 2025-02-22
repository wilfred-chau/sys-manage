package com.example.sysmanage.utils;

import java.security.SecureRandom;
import java.util.Base64;

public class SecretKeyGenerator {
    public static void main(String[] args) {
        try {
            // 使用SecureRandom生成密钥
            SecureRandom secureRandom = new SecureRandom();
            byte[] key = new byte[32];
            secureRandom.nextBytes(key);

            // Base64编码
            String secretKey = Base64.getEncoder().encodeToString(key);

            System.out.println("=============== JWT密钥 ===============");
            System.out.println(secretKey);
            System.out.println("=====================================");

            // 为了方便复制配置，直接打印yaml格式
            System.out.println("\n配置示例：");
            System.out.println("jwt:");
            System.out.println("  secret: " + secretKey);
            System.out.println("  expiration: 86400");

        } catch (Exception e) {
            System.err.println("生成密钥时发生错误：" + e.getMessage());
        }
    }
}