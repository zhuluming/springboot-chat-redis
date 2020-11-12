package com.laywerspringboot.edition.entity.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author:小七
 * @createTime:2020-11-12-20-36
 */
@ConfigurationProperties(prefix = "wx")
@Component
@Data
public class WXPay {
    private String appId;
    private String mchId;
    private String mchKey;
    private String notifyUrl;


}
