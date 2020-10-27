
package com.laywerspringboot.edition.Utils;


import com.laywerspringboot.edition.exception.SendMessageException;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;

/*
 * 会和云片的jar包冲突，需要导入之后重编译
 * @Author:小七
 * @createTime:2020-10-25-13-19
 */

public class TecentUtils {
    private static String uuid;

        public static String sendMsg(String phoneId) {
            Integer phoneUuid = RandomUtils.getPhoneUuid();
            uuid = phoneUuid.toString();
            try{

                Credential cred = new Credential("AKIDTiTxEE0SklVpvltYdAQQigbGfojkvs2E",
                        "3pMgjVINCk12pLaey3wYu3xcwKVqeYFg");

                HttpProfile httpProfile = new HttpProfile();
                httpProfile.setEndpoint("sms.tencentcloudapi.com");

                ClientProfile clientProfile = new ClientProfile();
                clientProfile.setHttpProfile(httpProfile);

                SmsClient client = new SmsClient(cred, "", clientProfile);

                SendSmsRequest req = new SendSmsRequest();
                String[] phoneNumberSet1 = {"+86"+phoneId};
                req.setPhoneNumberSet(phoneNumberSet1);


                String[] templateParamSet1 = {uuid};
                req.setTemplateParamSet(templateParamSet1);

                req.setTemplateID("754870");
                req.setSmsSdkAppid("1400439638");
                req.setSign("黄思琦的个人生活");

                SendSmsResponse resp = client.SendSms(req);

                System.out.println(SendSmsResponse.toJsonString(resp));
            } catch (TencentCloudSDKException e) {
                throw new SendMessageException();
            }
            return uuid;
        }
}

