package com.tyza66.user.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: tyza66
 * Date: 2023/7/10 13:50
 * Github: https://github.com/tyza66
 **/

@Api(tags = "用户模块")
@RestController
@RequestMapping("/user")
public class UserController {
    @ApiOperation("测试连通性")
    @GetMapping("/test")
    public String test(){
        return "ok";
    }

}
