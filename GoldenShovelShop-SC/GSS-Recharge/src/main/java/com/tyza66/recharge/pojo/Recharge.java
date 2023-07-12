package com.tyza66.recharge.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author: tyza66
 * Date: 2023/7/12 14:49
 * Github: https://github.com/tyza66
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recharge {
    Integer id;
    String topic;
    String traceno;
    Double mount;

}
