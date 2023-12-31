package com.tyza66.user.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.tyza66.user.dubbo_template.UseraccountControllerTemplate;
import com.tyza66.user.pojo.User;
import com.tyza66.user.service.impl.UseraccountServiceImpl;
import io.seata.core.context.RootContext;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: tyza66
 * Date: 2023/7/12 11:02
 * Github: https://github.com/tyza66
 **/

@RestController
@RequestMapping("/user")
@DubboService
public class UseraccountController implements UseraccountControllerTemplate {

    private static final Logger log = LoggerFactory.getLogger(UseraccountController.class);

    @Autowired
    private UseraccountServiceImpl useraccountService;

    @Override
    public Double addMoney(String username, Double money) {
        log.info("User Seata全局事务id=================>{}", RootContext.getXID());
        if(((User)StpUtil.getSession().get("user")).getUsername().equals(username)){
            return useraccountService.addMoney(username,money);
        }else{
            return -1.0;
        }
    }

    @Override
    public Double reduceMoney(String username, Double money) {
        if(((User)StpUtil.getSession().get("user")).getUsername().equals(username)){
            return useraccountService.reduceMoney(username,money);
        }else{
            return -1.0;
        }
    }

    //获取余额 只能自己查询自己的，别人的话直接返回-1
    @Override
    @RequestMapping("/getMoney")
    public Double getMoney(String username) {
        if(((User)StpUtil.getSession().get("user")).getUsername().equals(username)){
            return useraccountService.getMoney(username);
        }else{
            return -1.0;
        }
    }
}
