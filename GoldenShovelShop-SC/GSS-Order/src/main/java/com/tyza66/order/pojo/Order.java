package com.tyza66.order.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author: tyza66
 * Date: 2023/7/14 15:54
 * Github: https://github.com/tyza66
 **/


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    Integer id;
    String name;
    String status;
    Double price;
}
