package com.laywerspringboot.edition.service.impl;

import com.laywerspringboot.edition.dao.PricesDao;
import com.laywerspringboot.edition.entity.Prices;
import com.laywerspringboot.edition.service.PayService;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.service.BestPayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Service
@Slf4j
public class PayServiceImpl implements PayService {


    @Autowired
    @Qualifier(value = "wxBestPay")
    private BestPayService wxBestPay;

    @Resource
    private PricesDao pricesDao;

    @Override
    public String wxPay(String orderId, BigDecimal money) {
        // 准备订单信息,保存到数据库
        PayRequest payRequest = new PayRequest();
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_NATIVE);
        payRequest.setOrderId(orderId);
        payRequest.setOrderName("越南媳妇!!!");
        payRequest.setOrderAmount(money.doubleValue());

        // 发起支付
        PayResponse resp = wxBestPay.pay(payRequest);
        System.out.println(resp);
        String codeUrl = resp.getCodeUrl();
        return codeUrl;
    }

    @Override
    public String wxNotify(String s) {
        PayResponse resp = wxBestPay.asyncNotify(s);
        System.out.println("resp = " + resp);
        String orderId = resp.getOrderId();
        // 查询订单,获取订单金额
        BigDecimal money = BigDecimal.valueOf(0.01);

        // 对比金额
        Double userMoney = resp.getOrderAmount();
        if(money.doubleValue() - userMoney < 0.00001){
            // 支付成功
            // 修改订单状态   未支付 -> 已经支付
            // 返回用户支付成功信息
            return "<xml>\n" +
                    "  <return_code><![CDATA[SUCCESS]]></return_code>\n" +
                    "  <return_msg><![CDATA[OK]]></return_msg>\n" +
                    "</xml>";
        }else {
            return "订单金额有问题,拨打110.....";
        }
    }


    @Autowired
    @Qualifier(value = "aliBestPay")
    private BestPayService aliBestPay;

    @Override
    public String aliPay(String caseId, BigDecimal money) {
        // 准备订单信息,保存到数据库
        PayRequest payRequest = new PayRequest();
        payRequest.setPayTypeEnum(BestPayTypeEnum.ALIPAY_PC);
        payRequest.setOrderId(caseId);
        payRequest.setOrderName("为你的案号支付"+caseId);
        payRequest.setOrderAmount(money.doubleValue());
        //查询数据库
        Prices prices = pricesDao.queryByCaseId(caseId);
        prices.setPrice(money.doubleValue());
        //修改数据库
        pricesDao.update(prices);

        // 发起支付
        PayResponse resp = aliBestPay.pay(payRequest);
        log.info("【支付信息】"+resp.getBody());
        return resp.getBody();
    }

    @Override
    public String aliNotify(String s) {
        PayResponse resp = aliBestPay.asyncNotify(s);
        System.out.println("支付宝通知:" + resp);
        String caseId = resp.getOrderId();
        // 查询订单,获取订单金额
        BigDecimal money = BigDecimal.valueOf(0.01);

        // 对比金额
        Double userMoney = resp.getOrderAmount();
        if(money.doubleValue() - userMoney < 0.00001){
            // 支付成功
            // 修改订单状态
            Prices prices = pricesDao.queryByCaseId(caseId);
            prices.setState("1");
            pricesDao.update(prices);
            // 告诉支付宝,我知道了
            return "success";
        }else {
            // 金额有问题
            return null;
        }
    }


}
