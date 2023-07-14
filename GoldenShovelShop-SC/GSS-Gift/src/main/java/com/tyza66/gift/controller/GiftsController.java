package com.tyza66.gift.controller;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.tyza66.gift.service.impl.GiftsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: tyza66
 * Date: 2023/7/14 8:22
 * Github: https://github.com/tyza66
 **/

@RestController
@RequestMapping("/gifts")
public class GiftsController {

    @Autowired
    GiftsServiceImpl giftsService;

    @GetMapping("/num")
    public JSON getNum(){
        JSONObject obj = JSONUtil.createObj();
        int num = giftsService.getNum(1);
        obj.set("num",num);
        obj.set("code","200");
        return obj;
    }
}
