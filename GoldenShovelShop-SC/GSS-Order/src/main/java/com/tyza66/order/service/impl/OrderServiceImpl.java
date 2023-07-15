package com.tyza66.order.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tyza66.goods.pojo.Goods;
import com.tyza66.goods.service.GoodsService;
import com.tyza66.order.mapper.OrderMapper;
import com.tyza66.order.pojo.Order;
import com.tyza66.order.service.OrderService;
import com.tyza66.user.pojo.User;
import com.tyza66.user.service.UserService;
import com.tyza66.user.service.UseraccountService;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.dubbo.config.annotation.DubboReference;

/**
 * Author: tyza66
 * Date: 2023/7/14 16:01
 * Github: https://github.com/tyza66
 **/

public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @DubboReference(check = false)
    GoodsService goodsService;

    @DubboReference(check = false)
    UseraccountService useraccountService;

    @DubboReference(check = false)
    UserService userService;


    //因为时间精力的原因 这块我直接使用一步完成创建订单和完成交易了 0是失败 1是成功 2是使用优惠券了的成功
    @Override
    @GlobalTransactional
    public int buy() {
        //获得现在的数量
        Goods goods = goodsService.getGoods();
        if(goods.getStore()<=0){
            return 0;
        }
        User currentUser = userService.getCurrentUser();
        if(currentUser==null){
            return 0;
        }
        //创建订单
        baseMapper.insert(new Order(0,currentUser.getUsername(),"not",goods.getPrice()));

        //完成订单

        //扣钱
        useraccountService.reduceMoney(currentUser.getUsername(),goods.getPrice());
        //直接完成交易
        return 1;
    }
}
