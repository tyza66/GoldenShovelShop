package com.tyza66.user.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.tyza66.user.pojo.User;
import com.tyza66.user.service.impl.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Author: tyza66
 * Date: 2023/7/10 13:50
 * Github: https://github.com/tyza66
 **/

@Api(tags = "用户模块")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @ApiOperation("测试连通性")
    @SentinelResource(value = "test", blockHandler = "blockAdd")
    @GetMapping("/test")
    public JSON test(){
        JSONObject obj = JSONUtil.createObj();
        obj.set("code",200);
        obj.set("msg","ok");
        return obj;
    }

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public JSON login(@RequestBody User user){
        JSONObject obj = JSONUtil.createObj();
        User login = userService.login(user.getUsername(), user.getPassword());
        if(login!=null){
            obj.set("code","200");
            obj.set("msg","登陆成功");
            StpUtil.login(login.getPower());
        }else {
            obj.set("code","202");
            obj.set("msg","登陆失败");
        }
        return obj;
    }

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public JSON register(@RequestBody User user){
        JSONObject obj = JSONUtil.createObj();
        User register = userService.register(user.getUsername(), user.getPassword(), 0);
        if(register!=null){
            obj.set("code","200");
            obj.set("msg","注册成功");
        }else {
            obj.set("code","202");
            obj.set("msg","注册失败");
        }
        return obj;
    }


}
