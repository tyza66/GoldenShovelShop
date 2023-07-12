package com.tyza66.recharge.controller;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.payment.page.models.AlipayTradePagePayResponse;
import com.tyza66.user.dubbo_template.UseraccountControllerTemplate;
import com.tyza66.recharge.pojo.AliPay;
import com.tyza66.recharge.service.impl.RechargeServiceImpl;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: tyza66
 * Date: 2023/7/12 14:21
 * Github: https://github.com/tyza66
 **/

@RestController
@RequestMapping("/alipay")
public class AliPayController {
   @DubboReference(check = false)
    private UseraccountControllerTemplate useraccountControllerTemplate;

   @Autowired
    private RechargeServiceImpl rechargeService;

    //这里使用的easy版本的 传过来的标题好像不能中文 要修复这个问题可以使用完整版的sdk
    @GetMapping("/pay") // &subject=xxx&traceNo=xxx&totalAmount=xxx subject传递的是用户名
    public String pay(AliPay aliPay) {
        AlipayTradePagePayResponse response;
        try {
            //  发起API调用（以创建当面付收款二维码为例）
            response = Factory.Payment.Page()
                    .pay(URLEncoder.encode(aliPay.getSubject(), "UTF-8"), aliPay.getTraceNo(), String.valueOf(aliPay.getTotalAmount()), "");
        } catch (Exception e) {
            System.err.println("调用遭遇异常，原因：" + e.getMessage());
            throw new RuntimeException(e.getMessage(), e);
        }

        //跳转之前，直接调用充值成功的方法
        this.paySuccess(aliPay.getSubject(),aliPay.getTraceNo(),aliPay.getTotalAmount());
        return response.getBody();
    }

    //直接默认成功的方法
    @GlobalTransactional //分布式事务定义
    public String paySuccess(String username,String traceNo, double totalAmount) {
        //创建充值订单
        rechargeService.createRecharge(username+"支付宝充值"+totalAmount+"金币", traceNo, totalAmount);
        //充值订单状态改为成功
        rechargeService.changeStatusOK(traceNo);
        //给用户加钱
        useraccountControllerTemplate.addMoney(username, totalAmount);
        int i = 1/0;
        return "success";
    }

    //这个方法是充值结果的回调方法 需要将在这个api开放到互联网上
    // 但是由于现在的政策原因 没有什么好用的http端口映射工具，于是我们在其他地方调用直接默认充值成功的方法
    @PostMapping("/notify")  // 注意这里必须是POST接口
    public String payNotify(HttpServletRequest request) throws Exception {
        System.out.println(12);
        if (request.getParameter("trade_status").equals("TRADE_SUCCESS")) {
            System.out.println("=========支付宝异步回调========");

            Map<String, String> params = new HashMap<>();
            Map<String, String[]> requestParams = request.getParameterMap();
            for (String name : requestParams.keySet()) {
                params.put(name, request.getParameter(name));
                // System.out.println(name + " = " + request.getParameter(name));
            }

            String tradeNo = params.get("out_trade_no");
            String gmtPayment = params.get("gmt_payment");
            String alipayTradeNo = params.get("trade_no");
            // 支付宝验签
            if (Factory.Payment.Common().verifyNotify(params)) {
                // 验签通过
                System.out.println("交易名称: " + params.get("subject"));
                System.out.println("交易状态: " + params.get("trade_status"));
                System.out.println("支付宝交易凭证号: " + params.get("trade_no"));
                System.out.println("商户订单号: " + params.get("out_trade_no"));
                System.out.println("交易金额: " + params.get("total_amount"));
                System.out.println("买家在支付宝唯一id: " + params.get("buyer_id"));
                System.out.println("买家付款时间: " + params.get("gmt_payment"));
                System.out.println("买家付款金额: " + params.get("buyer_pay_amount"));
                //更新数据库中的订单信息
            }
        }
        return "success";
    }

}