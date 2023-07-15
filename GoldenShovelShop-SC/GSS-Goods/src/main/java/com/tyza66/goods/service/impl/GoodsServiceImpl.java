package com.tyza66.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tyza66.goods.mapper.GoodsMapper;
import com.tyza66.goods.pojo.Goods;
import com.tyza66.goods.service.GoodsService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

/**
 * Author: tyza66
 * Date: 2023/7/13 18:43
 * Github: https://github.com/tyza66
 **/

@Service
@DubboService
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {
    @Override
    public Goods getGoods() {
        return baseMapper.selectById(1);
    }

    @Override
    public void setGoods(Goods goods) {
        baseMapper.updateById(goods);
    }
}
