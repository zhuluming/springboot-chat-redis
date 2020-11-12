package com.laywerspringboot.edition.entity.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author:小七
 * @createTime:2020-11-12-20-36
 */
@ConfigurationProperties(prefix = "ali")
@Component
@Data
public class AliPay {
    private String appId;
    private String privateKey;
    private String aliPayPublicKey;
    private String returnUrl;
    private String notifyUrl;

}
