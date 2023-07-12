package com.tyza66.recharge.pojo;

import lombok.Data;

/**
 * Author: tyza66
 * Date: 2023/7/12 14:17
 * Github: https://github.com/tyza66
 **/

@Data
public class AliPay {
    private String traceNo;
    private double totalAmount;
    private String subject;
    private String alipayTraceNo;
}
