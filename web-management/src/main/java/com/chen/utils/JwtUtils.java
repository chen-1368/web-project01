package com.chen.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

public class JwtUtils {

    // 原始密钥
    private static final String RAW_SECRET = "chen";
    // 过期时间（12小时）
    private static final long EXPIRATION_MS = 12 * 60 * 60 * 1000;
    private static final SecretKey SECRET_KEY;

    static {
        byte[] keyBytes = RAW_SECRET.getBytes(StandardCharsets.UTF_8);
        // 创建32字节数组并使用循环模式填充
        byte[] extendedKey = new byte[32];
        if (keyBytes.length > 0) {  // 防止空密钥导致的异常
            for (int i = 0; i < extendedKey.length; i++) {
                // 使用取模运算实现循环填充
                extendedKey[i] = keyBytes[i % keyBytes.length];
            }
        }
        SECRET_KEY = Keys.hmacShaKeyFor(extendedKey);
    }

    // 禁止实例化
    private JwtUtils() {}

    /**
     * 生成 JWT 令牌
     */
    public static String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims) // 自定义数据
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MS)) // 过期时间
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256) // 指定算法和密钥
                .compact();
    }

    /**
     * 解析 JWT 令牌
     */
    public static Claims parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY) // 验证密钥
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}