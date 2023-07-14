package com.tyza66.gift.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tyza66.gift.mapper.GiftsMapper;
import com.tyza66.gift.mapper.OwnlistMapper;
import com.tyza66.gift.pojo.Gifts;
import com.tyza66.gift.pojo.Ownlist;
import com.tyza66.gift.service.GiftsService;
import com.tyza66.user.pojo.User;
import com.tyza66.user.service.UserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Author: tyza66
 * Date: 2023/7/14 8:09
 * Github: https://github.com/tyza66
 **/

@Service
public class GiftsServiceImpl extends ServiceImpl<GiftsMapper, Gifts> implements GiftsService {

    @DubboReference(check = false)
    UserService userService;

    @Resource
    OwnlistMapper ownlistMapper;
    @Override
    public int getNum(int id) {
        return baseMapper.selectById(id).getNum();
    }

    @Override
    public Gifts getGift() {
        return baseMapper.selectById(1);
    }

    @Override
    @Transactional
    public void giftIn(Integer giftid) {
        User currentUser = userService.getCurrentUser();
        if(currentUser==null){
            return;
        }
        Gifts gifts = baseMapper.selectById(1);
        Integer num = gifts.getNum();
        QueryWrapper<Ownlist> ownlistQueryWrapper = new QueryWrapper<>();
        ownlistQueryWrapper.eq("username",currentUser.getUsername());
        Ownlist ownlist = ownlistMapper.selectOne(ownlistQueryWrapper);
        if(num>=0&&ownlist==null){
            gifts.setNum(gifts.getNum()-1);
            baseMapper.updateById(gifts);
            ownlistMapper.insert(new Ownlist(0,currentUser.getUsername(),gifts.getId(),1,gifts.getPrice()));
        }
    }
}
