package com.tyza66.user.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.tyza66.user.dubbo_template.UserCheckControllerTemplate;
import com.tyza66.user.pojo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: tyza66
 * Date: 2023/7/12 9:59
 * Github: https://github.com/tyza66
 **/

@Api(tags = "用户检查")
@RestController
@RequestMapping("/user")
@DubboService
public class UserCheckController implements UserCheckControllerTemplate {

    //检查当前登录状态
    @ApiOperation("检查当前登录状态")
    @GetMapping("/checkLogin")
    public JSON checkLogin(){
        JSONObject obj = JSONUtil.createObj();
        if(StpUtil.isLogin()){
            User user = (User) StpUtil.getSession().get("user");
            obj.set("code","200");
            obj.set("user",user);
            obj.set("msg","已登录");
        }else {
            obj.set("code","202");
            obj.set("msg","未登录");
        }
        return obj;
    }

    //检查是否有权限
    @ApiOperation("检查是否有权限")
    @GetMapping("/checkPower")
    public JSON checkPower(String power){
        JSONObject obj = JSONUtil.createObj();
        boolean b = StpUtil.hasPermission("all");
        if(b){
            obj.set("code","200");
            obj.set("msg","有权限");
            return obj;
        }else if(StpUtil.hasPermission(power)){
            obj.set("code","200");
            obj.set("msg","有权限");
            return obj;
        }else{
            obj.set("code","202");
            obj.set("msg","无权限");
            return obj;
        }

    }

    //检查是否有角色
    @ApiOperation("检查是否有角色")
    @GetMapping("/checkRole")
    public JSON checkRole(String role){
        JSONObject obj = JSONUtil.createObj();
        boolean b = StpUtil.hasRole("all");
        if(b){
            obj.set("code","200");
            obj.set("msg","有角色");
            return obj;
        }else if(StpUtil.hasRole(role)){
            obj.set("code","200");
            obj.set("msg","有角色");
            return obj;
        }else{
            obj.set("code","202");
            obj.set("msg","无角色");
            return obj;
        }
    }

    @Override
    public boolean islogin() {
        return StpUtil.isLogin();
    }

    @Override
    public boolean hasPermission(String permission) {
        return StpUtil.hasPermission(permission);
    }

    @Override
    public boolean hasRole(String role) {
        return StpUtil.hasRole(role);
    }
}
