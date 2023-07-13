package com.tyza66.user.dubbo_template;

/**
 * Author: tyza66
 * Date: 2023/7/12 11:01
 * Github: https://github.com/tyza66
 **/

public interface UseraccountControllerTemplate {
    public Double addMoney(String username, Double money);
    public Double reduceMoney(String username, Double money);
    public Double getMoney(String username);
}
