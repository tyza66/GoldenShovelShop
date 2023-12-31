package com.tyza66.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tyza66.user.controller.UseraccountController;
import com.tyza66.user.mapper.UseraccountMapper;
import com.tyza66.user.pojo.Useraccount;
import com.tyza66.user.service.UseraccountService;
import io.seata.core.context.RootContext;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Author: tyza66
 * Date: 2023/7/12 11:05
 * Github: https://github.com/tyza66
 **/

@Service
@DubboService
public class UseraccountServiceImpl extends ServiceImpl<UseraccountMapper,Useraccount> implements UseraccountService {

    private static final Logger log = LoggerFactory.getLogger(UseraccountController.class);
    @Override
    public double addMoney(String username, Double money) {
        log.info("User Seata全局事务id=================>{}", RootContext.getXID());
        QueryWrapper<Useraccount> useraccountQueryWrapper = new QueryWrapper<>();
        useraccountQueryWrapper.eq("username",username);
        Useraccount useraccount = baseMapper.selectOne(useraccountQueryWrapper);
        useraccount.setMoney(useraccount.getMoney()+money);
        baseMapper.updateById(useraccount);
        return useraccount.getMoney();
    }

    @Override
    public double reduceMoney(String username, Double money) {
        QueryWrapper<Useraccount> useraccountQueryWrapper = new QueryWrapper<>();
        useraccountQueryWrapper.eq("username",username);
        Useraccount useraccount = baseMapper.selectOne(useraccountQueryWrapper);
        useraccount.setMoney(useraccount.getMoney()-money);
        baseMapper.updateById(useraccount);
        return useraccount.getMoney();
    }

    @Override
    public Double getMoney(String username) {
        QueryWrapper<Useraccount> useraccountQueryWrapper = new QueryWrapper<>();
        useraccountQueryWrapper.eq("username",username);
        Useraccount useraccount = baseMapper.selectOne(useraccountQueryWrapper);
        return useraccount.getMoney();
    }
}
