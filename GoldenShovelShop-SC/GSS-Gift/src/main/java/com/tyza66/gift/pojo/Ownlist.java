package com.tyza66.gift.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author: tyza66
 * Date: 2023/7/14 8:02
 * Github: https://github.com/tyza66
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ownlist {
    Integer id;
    String username;
    Integer giftid;
    Integer num;

    Double price;
}
