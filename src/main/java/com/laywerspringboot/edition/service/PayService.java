package com.laywerspringboot.edition.service;

import java.math.BigDecimal;

public interface PayService {


    /**
     *
     * @param orderId   订单id
     * @param money     订单金额
     * @return   code_url
     */
    String wxPay(String orderId, BigDecimal money);


    /**
     * 接收微信回调
     * @param s
     * @return
     */
    String wxNotify(String s);


    /**
     *
     * @param orderId   订单id
     * @param money     订单金额
     * @return   body
     */
    String aliPay(String orderId, BigDecimal money);

    /**
     * 接收ali回调
     * @param s
     * @return
     */
    String aliNotify(String s);
}
