package com.tyza66.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tyza66.user.mapper.UserMapper;
import com.tyza66.user.pojo.User;
import com.tyza66.user.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * Author: tyza66
 * Date: 2023/7/10 15:00
 * Github: https://github.com/tyza66
 **/

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
    @Override
    public User login(String username, String password) {
        List<User> login = baseMapper.login(username, password);
        if(login.size()>=1){
            return login.get(0);
        }
        return null;
    }
}
