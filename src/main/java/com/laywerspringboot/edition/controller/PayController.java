package com.laywerspringboot.edition.controller;

import com.laywerspringboot.edition.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @Author:小七
 * @createTime:2020-11-01-18-41
 */
@RestController
public class PayController {
    @Autowired
    private PayService payService;



    @RequestMapping("/wxsync")
    public String wxNotify(@RequestBody String s){
        return payService.wxNotify(s);
    }

    // 让前端定时向后台发送ajax请求,查询订单状态,如果订单状态被修改为已支付,前端通过locaiton.href跳转到指定页面


    @RequestMapping(value = "alipay",produces = "text/html;charset=utf-8")
    public String alipay(String caseId,Double money){
        String body = payService.aliPay(caseId, BigDecimal.valueOf(money));
        return body;
    }

    @RequestMapping("/alisync")
    public String aliSycn(@RequestBody String s){
        return payService.aliNotify(s);
    }
}
