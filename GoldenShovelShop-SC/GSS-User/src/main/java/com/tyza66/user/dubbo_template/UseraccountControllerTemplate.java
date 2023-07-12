package com.tyza66.user.dubbo_template;

/**
 * Author: tyza66
 * Date: 2023/7/12 11:01
 * Github: https://github.com/tyza66
 **/

public interface UseraccountControllerTemplate {
    Double addMoney(String username, Double money);
    Double reduceMoney(String username, Double money);
    Double getMoney(String username);
}
