package com.laywerspringboot.edition.Utils;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @Author:小七
 * @createTime:2020-10-23-19-59
 */
public class R extends HashMap<String ,Object> implements Serializable {
    public static final long serialVersionUid = 1L;

    /**
     * 往R中存放数据
     * @param key
     * @param value
     * @return
     */
    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }
    public static R isError(int code, String msg){
        R r = new R();
        r.put("flag", code);
        r.put("msg", msg);
        return r;
    }
    public static R error(String msg){
        return isError(0, msg);
    }
    /**
     * 构造简单json封装
     * @param code
     * @param msg
     * @return
     */
    public static R isOk(int code, String msg){
        R r = new R();
        r.put("flag", code);
        r.put("msg", msg);
        return r;
    }

    /**
     * 可以登录返回
     * @param msg
     * @return
     */
    public static R loginOk(String msg){
        return isOk(1,msg);
    }


    /**
     * 可以注册返回1
     * @param msg
     * @return
     */
    public static R registerOk(String msg){
        return isOk(1,msg);
    }

    /**
     * 无法注册返回0
     * @param msg
     * @return
     */
    public static R registerError(String msg){
        return isOk(0, msg);
    }

    /**
     * 返回验证码
     * @param uuid
     * @return
     */
    public static R isUuid(String uuid){
        R r = new R();
        r.put("uuid", uuid);
        return r;
    }
}
