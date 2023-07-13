package com.tyza66.recharge.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tyza66.recharge.mapper.RechargeMapper;
import com.tyza66.recharge.pojp.Recharge;
import com.tyza66.recharge.service.RechargeService;
import com.tyza66.user.service.UseraccountService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: tyza66
 * Date: 2023/7/13 10:29
 * Github: https://github.com/tyza66
 **/

@Service
public class RechargeServiceImpl extends ServiceImpl<RechargeMapper, Recharge> implements RechargeService {
    @DubboReference(check = false)
    UseraccountService useraccountService;


    @Override
    public void recharge(String topic, String tranceno, Double amount) {
        Recharge recharge = new Recharge(0, topic, tranceno, amount, 0);
        baseMapper.insert(recharge);
    }

    @Override
    public void makeStatusOk(String tranceno) {
        QueryWrapper<Recharge> rechargeQueryWrapper = new QueryWrapper<>();
        rechargeQueryWrapper.eq("traceno", tranceno);
        List<Recharge> recharges = baseMapper.selectList(rechargeQueryWrapper);
        Recharge recharge = recharges.get(0);
        recharge.setStatus(1);
        baseMapper.updateById(recharge);
    }

    @Override
    @GlobalTransactional
    public void OK96(String topic, String tranceno, Double amount) {
        System.out.println("giaogiao"+RootContext.getXID());
        //床架充值订单
        recharge(topic, tranceno, amount);
        //不知道为啥 分布式事务回滚到这个地方就不滚了
        //直接将充值订单状态修改为成功
        makeStatusOk(tranceno);
        //给用户加钱
        useraccountService.addMoney(topic,amount);
        //int err = 1/0;
    }
}
