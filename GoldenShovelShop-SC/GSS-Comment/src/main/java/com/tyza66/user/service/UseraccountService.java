package com.tyza66.user.service;

/**
 * Author: tyza66
 * Date: 2023/7/12 10:49
 * Github: https://github.com/tyza66
 **/

public interface UseraccountService {
    double addMoney(String username, Double money);
    double reduceMoney(String username, Double money);
    Double getMoney(String username);
}
