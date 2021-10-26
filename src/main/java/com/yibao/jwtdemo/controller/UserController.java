package com.yibao.jwtdemo.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.yibao.jwtdemo.entity.User;
import com.yibao.jwtdemo.service.UserService;
import com.yibao.jwtdemo.util.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liyi
 * @create 2021 -10 -26 -10:48
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 方法：登录，返回token
     * @param user
     * @return
     */
    @GetMapping("login")
    public Map<String, Object> login(User user) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            User user1 = userService.login(user);
            HashMap<String, String> payload = new HashMap<>();
            // 生成token，并封装返回
            payload.put("userId", user1.getId().toString());
            payload.put("userName", user1.getUserName());
            String token = JWTUtils.getToken(payload);
            map.put("status", 200);
            map.put("token", token);
            map.put("msg", "登录成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", 500);
            map.put("msg", "登录失败");
        }
        return map;

    }


    /**
     * 方法：校验token
     * @param token
     * @return
     */
    @RequestMapping("verify")
    public Map<String, Object> verify(String token) {
        Map<String, Object> map = new HashMap<>();
        try {
            DecodedJWT verify = JWTUtils.verify(token);
            // 校验通过，可以取信息
            map.put("status", 200);
            map.put("msg", "验证token通过");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", 500);
            map.put("msg", e.getCause());
        }
        return map;
    }

}
