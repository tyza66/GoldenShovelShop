package com.tyza66.gift.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tyza66.gift.mapper.GiftsMapper;
import com.tyza66.gift.pojo.Gifts;
import com.tyza66.gift.service.GiftsService;
import org.springframework.stereotype.Service;

/**
 * Author: tyza66
 * Date: 2023/7/14 8:09
 * Github: https://github.com/tyza66
 **/

@Service
public class GiftsServiceImpl extends ServiceImpl<GiftsMapper, Gifts> implements GiftsService {
    @Override
    public int getNum(int id) {
        return baseMapper.selectById(id).getNum();
    }

    @Override
    public Gifts getGift() {
        return baseMapper.selectById(1);
    }
}
