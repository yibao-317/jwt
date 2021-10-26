package com.yibao.jwtdemo.service.impl;

import com.yibao.jwtdemo.entity.User;
import com.yibao.jwtdemo.mapper.UserMapper;
import com.yibao.jwtdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liyi
 * @create 2021 -10 -26 -10:50
 */
@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(User user) {
        return userMapper.login(user);
    }
}
