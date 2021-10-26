package com.yibao.jwtdemo.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

/**
 * 自定义 JWT 工具类
 *
 * @author liyi
 * @create 2021 -10 -26 -10:01
 */
public class JWTUtils {
    // 签名密钥
    private static final String SING = "@GlasssixMCR!@#$%^&*";

    /**
     * 方法：生成token
     * @param map
     * @return
     */
    public static String getToken(Map<String, String> map) {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE, 1);  // 过期时间：1d
        // 创建对象
        JWTCreator.Builder builder = JWT.create();
        // 设置 payload
        map.forEach((k, v) -> {
            builder.withClaim(k, v);
        });
        // 设置过期时间和签名
        String token = builder.withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(SING));
        // 返回 token
        return token;
    }

    /**
     * 方法：验证token合法性，并返回对象，可获取封装信息
     * @param token
     * @return
     */
    public static DecodedJWT verify(String token){
        return JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
    }


}
