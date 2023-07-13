package com.tyza66.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tyza66.user.mapper.UserMapper;
import com.tyza66.user.mapper.UseraccountMapper;
import com.tyza66.user.pojo.User;
import com.tyza66.user.pojo.Useraccount;
import com.tyza66.user.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Author: tyza66
 * Date: 2023/7/10 15:00
 * Github: https://github.com/tyza66
 **/

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UseraccountMapper useraccountMapper;
    @Override
    public User login(String username, String password) {
        List<User> login = baseMapper.login(username, password);
        if (login.size() >= 1) {
            return login.get(0);
        }
        return null;
    }

    @Override
    public User searchByUsername(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    @Transactional
    public User register(String username, String password, Integer power) {
        if (searchByUsername(username) != null) {
            return null;
        }
        User user = new User();
        user.setId(0);
        user.setUsername(username);
        user.setPassword(password);
        user.setPower(power);
        useraccountMapper.insert(new Useraccount(0, 0.0, username));
        baseMapper.insert(user);
        return user;
    }
}
