package com.yibao.jwtdemo;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

class JwtDemoApplicationTests {

    /**
     * 方法：生成 JWT-token
     */
    @Test
    void createToken() {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND, 60);  // 60s

        String token = JWT.create()
                .withClaim("userId", 1)       // payload部分：可以用来存储用户信息（不要有密码等敏感信息）
                .withClaim("userName", "yibao")
                .withClaim("权限信息", "[user:*:*]")
                .withExpiresAt(instance.getTime())   // 指定令牌过期时间
                .sign(Algorithm.HMAC256("GlasssixMRC#@!"));// 签名
        System.out.println(token);

    }

    /**
     * 方法：反解 token，拿到 payload 里面的信息
     */
    @Test
    public void takeToken() {
        // 创建验证对象
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256("GlasssixMRC#@!")).build();
        // 验证，并获取信息
        DecodedJWT jwt = verifier.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyLmnYPpmZDkv6Hmga8iOiJbdXNlcjoqOipdIiwidXNlck5hbWUiOiJ5aWJhbyIsImV4cCI6MTYzNTIxMjk5NSwidXNlcklkIjoxfQ.zA3R_29BYJG5DfQ6uh2Cc9bjlDZsvJMAd7XCdqCSPYE");
        Integer userId = jwt.getClaim("userId").asInt();  // 注意：什么类型存的，什么类型取
        String userName = jwt.getClaim("userName").asString();
        System.out.println("账号id： " + userId + "  账号： " + userName);
        System.out.println("过期时间： " + jwt.getExpiresAt());


    }

}
