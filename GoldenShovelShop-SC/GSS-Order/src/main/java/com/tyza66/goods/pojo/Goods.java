package com.tyza66.goods.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Author: tyza66
 * Date: 2023/7/13 18:38
 * Github: https://github.com/tyza66
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Goods implements Serializable {
    Integer id;
    String title;
    String info;
    Double price;
    Integer store;
}
