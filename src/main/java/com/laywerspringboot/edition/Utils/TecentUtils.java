
package com.laywerspringboot.edition.Utils;


import com.laywerspringboot.edition.entity.properties.TecentProperties;
import com.laywerspringboot.edition.exception.SendMessageException;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
 * 会和云片的jar包冲突，需要导入之后重编译
 * @Author:小七
 * @createTime:2020-10-25-13-19
 */

@Component
public class TecentUtils {
    private  String uuid;
    @Autowired
    private TecentProperties tecentProperties;

        public  String sendMsg(String phoneId) {
            Integer phoneUuid = RandomUtils.getPhoneUuid();
            uuid = phoneUuid.toString();
            try{

                Credential cred = new Credential(tecentProperties.getSecretId(),
                        tecentProperties.getSecretKey());

                HttpProfile httpProfile = new HttpProfile();
                httpProfile.setEndpoint(tecentProperties.getEndPoint());

                ClientProfile clientProfile = new ClientProfile();
                clientProfile.setHttpProfile(httpProfile);

                SmsClient client = new SmsClient(cred, "", clientProfile);
                System.out.println(phoneId);
                SendSmsRequest req = new SendSmsRequest();
                String[] phoneNumberSet1 = {"+86"+phoneId};
                req.setPhoneNumberSet(phoneNumberSet1);


                String[] templateParamSet1 = {uuid};
                System.out.println(123456);
                req.setTemplateParamSet(templateParamSet1);

                req.setTemplateID(tecentProperties.getTemplateID());
                req.setSmsSdkAppid(tecentProperties.getSmsSdkAppid());
                req.setSign(tecentProperties.getSign());

                SendSmsResponse resp = client.SendSms(req);
                System.out.println(SendSmsResponse.toJsonString(resp));
                return uuid;
            } catch (TencentCloudSDKException e) {
                throw new SendMessageException();
            }

        }
}

