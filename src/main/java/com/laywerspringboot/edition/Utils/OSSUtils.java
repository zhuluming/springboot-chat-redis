/*
package com.laywerspringboot.edition.Utils;

import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;

import java.net.URL;
import java.util.Date;

*/
/**
 * @Author:小七
 * @createTime:2020-10-26-19-47
 *//*

public class OSSUtils {
    */
/**
     * 图片
     * @param photoName
     * @return
     *//*

    public static URL getJpgUrl(String photoName){
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "oss-accelerate.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
        String accessKeyId = "LTAI4GHv4TkFAPC3zicB3aRo";
        String accessKeySecret = "76RgJBI7LDgs5cWWsRloZGnZHHlSRl";
        String bucketName = "laywertest";
        String objectName = photoName;
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 将图片缩放为固定宽高100 px后，再旋转90°。
        String style = "image/resize,m_fixed,w_100,h_100/rotate,90";
        // 指定签名URL过期时间为10分钟。
        Date expiration = new Date(new Date().getTime() + 1000 * 60 * 10 );
        GeneratePresignedUrlRequest req = new GeneratePresignedUrlRequest(bucketName, objectName, HttpMethod.GET);
        req.setExpiration(expiration);
        req.setProcess(style);
        URL signedUrl = ossClient.generatePresignedUrl(req);
        System.out.println(signedUrl);
        // 关闭OSSClient。
        ossClient.shutdown();
        //返回URL
        return signedUrl;
    }
}
*/
