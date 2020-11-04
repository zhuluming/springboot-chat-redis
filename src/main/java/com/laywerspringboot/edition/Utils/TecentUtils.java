
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

                Credential cred = new Credential("AKIDVjitnyOZpocIiscSuIPL2UfcIl6BYg39",
                        "1lAQPDl46tRm0zpQlqhHdolW0ECxVq0u");

                HttpProfile httpProfile = new HttpProfile();
                httpProfile.setEndpoint("sms.tencentcloudapi.com");

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

                req.setTemplateID("754870");
                req.setSmsSdkAppid("1400439638");
                req.setSign("黄思琦的个人生活");

                SendSmsResponse resp = client.SendSms(req);
                System.out.println(SendSmsResponse.toJsonString(resp));
                return uuid;
            } catch (TencentCloudSDKException e) {
                throw new SendMessageException();
            }

        }
}

