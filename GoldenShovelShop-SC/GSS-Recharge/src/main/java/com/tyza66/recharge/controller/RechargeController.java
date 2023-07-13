package com.tyza66.recharge.controller;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.tyza66.recharge.service.impl.RechargeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: tyza66
 * Date: 2023/7/13 10:46
 * Github: https://github.com/tyza66
 **/

@RestController
@RequestMapping("/alipay")
public class RechargeController {
    @Autowired
    RechargeServiceImpl rechargeService;
    @GetMapping("/pay") //subject=admin&traceNo=55555&totalAmount=100
    public JSON pay(String subject, Integer traceNo, Double totalAmount) {
        JSONObject obj = JSONUtil.createObj();
        rechargeService.OK96(subject, traceNo, totalAmount);
        obj.set("code", "200");
        obj.set("msg", "默认支付完成");
        return obj;
    }
}
