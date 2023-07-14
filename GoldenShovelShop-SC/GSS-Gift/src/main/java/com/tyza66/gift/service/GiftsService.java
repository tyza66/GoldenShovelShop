package com.tyza66.gift.service;

import com.tyza66.gift.pojo.Gifts;

/**
 * Author: tyza66
 * Date: 2023/7/14 8:09
 * Github: https://github.com/tyza66
 **/

public interface GiftsService {

    public int getNum(int id);

    public Gifts getGift();

    public void giftIn(String username,Integer giftid,Double price);
}
