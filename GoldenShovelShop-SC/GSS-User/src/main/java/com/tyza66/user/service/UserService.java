package com.tyza66.user.service;

import com.tyza66.user.pojo.User;

/**
 * Author: tyza66
 * Date: 2023/7/10 14:59
 * Github: https://github.com/tyza66
 **/

public interface UserService{
    User login(String username, String password);
}
