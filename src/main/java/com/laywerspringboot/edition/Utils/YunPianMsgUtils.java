//package com.laywerspringboot.edition.Utils;
//
//
//import com.yunpian.sdk.YunpianClient;
//import com.yunpian.sdk.model.Result;
//import com.yunpian.sdk.model.SmsSingleSend;
//
//import java.util.Map;
//
///**
// * @Author:小七
// * @createTime:2020-10-24-15-59
// */
//public class YunPianMsgUtils {
//    /**云片api发送短信验证码
//     * 发送验证码并返回验证码的值
//     * @return 验证码的值
//     */
//    public static String sendMsg(String phoneid){
//        //初始化clnt,使用单例方式
//         YunpianClient clnt = new YunpianClient("ec25476854026e1867ea17123f335888").init();
//        YunpianClient clnt = new YunpianClient("e864a2a3bb00b4b426435f85d75ebccd").init();
//        //定义验证码
//       Integer phoneUuid = RandomUtils.getPhoneUuid();
//        //发送短信API
//        Map<String, String> param = clnt.newParam(2);
//        param.put(YunpianClient.MOBILE, phoneid);
//        //param.put(YunpianClient.TEXT, "【我的个人生活日志】您的验证码是"+phoneUuid+"。如非本人操作，请忽略本短信");
//
//        param.put(YunpianClient.TEXT, "【赵伟风】您的验证码是"+phoneUuid+"。如非本人操作，请忽略本短信");
//
//        Result<SmsSingleSend> r = clnt.sms().single_send(param);
//        Integer code = r.getCode();
//        System.out.println(r);
//        //获取返回结果，返回码:r.getCode(),返回码描述:r.getMsg(),API结果:r.getData(),其他说明:r.getDetail(),调用异常:r.getThrowable()
//
//        //账户:clnt.user().* 签名:clnt.sign().* 模版:clnt.tpl().* 短信:clnt.sms().* 语音:clnt.voice().* 流量:clnt.flow().* 隐私通话:clnt.call().*
//
//        //释放clnt
//        clnt.close();
//        return phoneUuid.toString();
//    }
//
//}
