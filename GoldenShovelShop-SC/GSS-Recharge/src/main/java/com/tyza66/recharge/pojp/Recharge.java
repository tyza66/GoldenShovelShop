package com.tyza66.recharge.pojp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author: tyza66
 * Date: 2023/7/13 10:25
 * Github: https://github.com/tyza66
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recharge {
    Integer id;
    String topic;
    String traceno;
    Double amount;
    Integer status;
}
