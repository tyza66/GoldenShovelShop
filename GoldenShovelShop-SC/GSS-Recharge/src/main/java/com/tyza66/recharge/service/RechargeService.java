package com.tyza66.recharge.service;

/**
 * Author: tyza66
 * Date: 2023/7/13 10:28
 * Github: https://github.com/tyza66
 **/

public interface RechargeService {
    public void recharge(String topic,Integer tranceno,Double amount);

    public void makeStatusOk(Integer tranceno);

    public void OK96(String topic, Integer tranceno, Double amount);
}
