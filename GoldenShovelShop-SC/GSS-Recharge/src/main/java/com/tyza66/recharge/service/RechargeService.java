package com.tyza66.recharge.service;

/**
 * Author: tyza66
 * Date: 2023/7/12 15:19
 * Github: https://github.com/tyza66
 **/

public interface RechargeService {
    void createRecharge(String topic, String traceno, Double mount);

    void changeStatusOK(String traceno);
}
