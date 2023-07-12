package com.tyza66.recharge.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tyza66.recharge.mapper.RechargeMapper;
import com.tyza66.recharge.pojo.Recharge;
import com.tyza66.recharge.service.RechargeService;
import org.springframework.stereotype.Service;

/**
 * Author: tyza66
 * Date: 2023/7/12 15:19
 * Github: https://github.com/tyza66
 **/

@Service
public class RechargeServiceImpl extends ServiceImpl<RechargeMapper, Recharge> implements RechargeService{

    @Override
    public void createRecharge(String topic, String traceno, Double mount) {
        Recharge recharge = new Recharge(0, topic, traceno, mount,0);
        baseMapper.insert(recharge);
    }

    @Override
    public void changeStatusOK(String traceno) {
        QueryWrapper<Recharge> rechargeQueryWrapper = new QueryWrapper<>();
        rechargeQueryWrapper.eq("traceno",traceno);
        Recharge recharge = baseMapper.selectOne(rechargeQueryWrapper);
        recharge.setStatus(1);
        baseMapper.updateById(recharge);
    }
}
