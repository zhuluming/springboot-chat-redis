package com.laywerspringboot.edition.config;

import com.laywerspringboot.edition.entity.properties.AliPay;
import com.laywerspringboot.edition.entity.properties.WXPay;
import com.lly835.bestpay.config.AliPayConfig;
import com.lly835.bestpay.config.WxPayConfig;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author:小七
 * @createTime:2020-11-12-20-59
 */
@Configuration
public class PayConfig {
    @Autowired
    private AliPay aliPay;
    @Autowired
    private WXPay wxPay;


    @Bean
    public WxPayConfig wxPayConfig(){
        WxPayConfig wxPayConfig = new WxPayConfig();
        wxPayConfig.setAppId(wxPay.getAppId());
        wxPayConfig.setMchId(wxPay.getMchId());
        wxPayConfig.setMchKey(wxPay.getMchKey());
        wxPayConfig.setNotifyUrl(wxPay.getNotifyUrl());
        return wxPayConfig;
    }

    @Bean
    public BestPayServiceImpl wxBestPay(WxPayConfig wxPayConfig){
        BestPayServiceImpl bestPayService = new BestPayServiceImpl();
        bestPayService.setWxPayConfig(wxPayConfig);
        return bestPayService;
    }

    @Bean
    public AliPayConfig aliPayConfig(){
        AliPayConfig aliPayConfig = new AliPayConfig();
        aliPayConfig.setAppId(aliPay.getAppId());
        aliPayConfig.setPrivateKey(aliPay.getPrivateKey());
        aliPayConfig.setAliPayPublicKey(aliPay.getAliPayPublicKey());
        aliPayConfig.setReturnUrl(aliPay.getReturnUrl());
        aliPayConfig.setNotifyUrl(aliPay.getNotifyUrl());
        return aliPayConfig;
    }

    @Bean
    public BestPayServiceImpl aliBestPay(AliPayConfig aliPayConfig){
        BestPayServiceImpl bestPayService = new BestPayServiceImpl();
        bestPayService.setAliPayConfig(aliPayConfig);
        return bestPayService;
    }
}
