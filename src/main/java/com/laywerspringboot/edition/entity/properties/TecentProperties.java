package com.laywerspringboot.edition.entity.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author:小七
 * @createTime:2020-11-06-14-11
 */
@ConfigurationProperties(prefix = "tecent")
@Component
@Data
public class TecentProperties {
    private String secretId;
    private String secretKey;
    private String endPoint;
    private String TemplateID;
    private String SmsSdkAppid;
    private String Sign;
}
