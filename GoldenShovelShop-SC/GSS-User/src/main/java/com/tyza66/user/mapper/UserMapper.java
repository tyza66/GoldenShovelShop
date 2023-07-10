package com.tyza66.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tyza66.user.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Author: tyza66
 * Date: 2023/7/10 14:50
 * Github: https://github.com/tyza66
 **/

public interface UserMapper extends BaseMapper<User> {
    List<User> login(@Param("username") String username,@Param("password") String password);
}
