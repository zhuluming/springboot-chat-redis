package com.laywerspringboot.edition.entity.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author:小七
 * @createTime:2020-11-06-14-00
 */
@ConfigurationProperties(prefix = "oss")
@Component
@Data
public class OSS {

    private  String endpoint;// = "oss-accelerate.aliyuncs.com";

    private  String accessKeyId;// = "LTAI4G2Na3UUSWsFwELHTLVw";

    private  String accessKeySecret;// = "POoMOcqWfB4YgyLGAXcIRFveJycQ9j";

    private  String bucketName;// = "laywertest";

    private  String filedir;// = "image/";

}
