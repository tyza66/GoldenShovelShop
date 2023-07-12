package com.tyza66.user.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: tyza66
 * Date: 2023/7/12 9:59
 * Github: https://github.com/tyza66
 **/

@Api("用户检查")
@RestController
@RequestMapping("/user")
public class UserCheckController {

    //检查当前登录状态
    @ApiOperation("检查当前登录状态")
    @GetMapping("/checkLogin")
    public JSON checkLogin(){
        JSONObject obj = JSONUtil.createObj();
        if(StpUtil.isLogin()){
            obj.set("code","200");
            obj.set("msg","已登录");
        }else {
            obj.set("code","202");
            obj.set("msg","未登录");
        }
        return obj;
    }

    //检查是否有权限

    //检查是否有角色


}
