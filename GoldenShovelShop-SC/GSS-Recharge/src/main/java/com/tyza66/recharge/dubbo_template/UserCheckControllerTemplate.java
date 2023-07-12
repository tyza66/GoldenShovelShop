package com.tyza66.recharge.dubbo_template;

import cn.hutool.json.JSON;

/**
 * Author: tyza66
 * Date: 2023/7/12 10:18
 * Github: https://github.com/tyza66
 **/

public interface UserCheckControllerTemplate {
    JSON checkLogin();
    JSON checkPower(String power);
    JSON checkRole(String role);

    boolean islogin();

    boolean hasPermission(String permission);

    boolean hasRole(String role);

    String getNowUsername();
}
