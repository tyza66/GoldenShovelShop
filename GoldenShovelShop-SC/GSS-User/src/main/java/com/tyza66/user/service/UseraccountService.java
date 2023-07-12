package com.tyza66.user.service;

/**
 * Author: tyza66
 * Date: 2023/7/12 10:49
 * Github: https://github.com/tyza66
 **/

public interface UseraccountService {
    void addMoney(Integer id, Double money);
    void reduceMoney(Integer id, Double money);
    Double getMoney(Integer id);
}
