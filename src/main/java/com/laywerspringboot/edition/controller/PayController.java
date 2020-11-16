package com.laywerspringboot.edition.controller;

import com.laywerspringboot.edition.entity.Newspaper;
import com.laywerspringboot.edition.entity.dto.UserCheck;
import com.laywerspringboot.edition.service.NewspaperService;
import com.laywerspringboot.edition.service.PayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @Author:小七
 * @createTime:2020-11-01-18-41
 */
@CrossOrigin()
@RestController
@Api(description = "支付模块api",value = "支付")
public class PayController {
    @Autowired
    private PayService payService;
    @Autowired
    private NewspaperService newspaperService;


    @CrossOrigin()
    @RequestMapping("/wxsync")
    public String wxNotify(@RequestBody String s){
        return payService.wxNotify(s);
    }

    // 让前端定时向后台发送ajax请求,查询订单状态,如果订单状态被修改为已支付,前端通过locaiton.href跳转到指定页面

    @CrossOrigin()
    @RequestMapping(value = "alipay",produces = "text/html;charset=utf-8")
    @ApiOperation(value = "阿里支付接口")
    public String alipay(@RequestBody UserCheck userCheck){
        //todo  没改版面，然后支付状态好像也有问题
        //todo 支付的时候把案号改了
        Newspaper update = newspaperService.updatePay(userCheck);
        String body = payService.aliPay(userCheck.getCaseId(), BigDecimal.valueOf(userCheck.getPrice()));
        return body;
    }
    @CrossOrigin()
    @RequestMapping("/alisync")
    public String aliSycn(@RequestBody String s){
        return payService.aliNotify(s);
    }
}
