package com.yibao.jwtdemo.mapper;

import com.yibao.jwtdemo.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author liyi
 * @create 2021 -10 -26 -10:34
 */
@Mapper
public interface UserMapper {
    // 登录
    User login(User user);

}
