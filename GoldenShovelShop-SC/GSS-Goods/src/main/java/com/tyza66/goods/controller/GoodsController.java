package com.tyza66.goods.controller;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.tyza66.goods.pojo.Goods;
import com.tyza66.goods.service.impl.GoodsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: tyza66
 * Date: 2023/7/13 18:49
 * Github: https://github.com/tyza66
 **/

@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    GoodsServiceImpl goodsService;

    @GetMapping("/thing")
    public JSON getGoods(){
        JSONObject obj = JSONUtil.createObj();
        Goods goods = goodsService.getGoods();
        if(goods==null){
            obj.set("code","202");
            obj.set("msg","获取失败");
        }else{
            obj.set("code","200");
            obj.set("good",goods);
            obj.set("msg","获取成功");
        }
        return  obj;
    }
}
