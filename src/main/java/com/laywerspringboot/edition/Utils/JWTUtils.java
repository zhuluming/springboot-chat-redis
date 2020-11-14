package com.laywerspringboot.edition.Utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Map;

/**
 * @Author:小七
 * @createTime:2020-10-26-21-57
 */
public class JWTUtils {
    private static String TOKEN = "token!Q@W3e4r";
    /**
     * 生成token
     * @param map  //传入payload
     * @return 返回token
     */
    public static String getToken(Map<String,String> map){
        JWTCreator.Builder builder = JWT.create();
        map.forEach((k,v)->{
            builder.withClaim(k,v);
        });
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DAY_OF_WEEK,5);
        builder.withExpiresAt(instance.getTime());
        return builder.sign(Algorithm.HMAC256(TOKEN)).toString();
    }
    /**
     * 验证token
     * @param token
     * @return
     */
    public static DecodedJWT verify(String token){
        return JWT.require(Algorithm.HMAC256(TOKEN)).build().verify(token);
    }
    /**
     * 获取token中payload
     * @param token
     * @return
     */
    public static DecodedJWT getToken(String token){
        return JWT.require(Algorithm.HMAC256(TOKEN)).build().verify(token);
    }
    /**
     * 获取token中的id
     * @param request
     * @return
     */
    public static Integer getTokenId(HttpServletRequest request) {
        //从请求头中获取token
        String token = request.getHeader("token");
        //验证token
        DecodedJWT decodedJWT = JWTUtils.verify(token);
        //log.info("用户id: [{}]",decodedJWT.getClaim("id").asString());
        //log.info("用户name: [{}]",decodedJWT.getClaim("name").asString());
        String idStr = decodedJWT.getClaim("id").asString();
        return Integer.valueOf(idStr);
    }

    /**
     * 获取角色
     * @param request
     * @return
     */
    public static String getTokenRole(HttpServletRequest request) {
        //从请求头中获取token
        String token = request.getHeader("token");
        //验证token
        DecodedJWT decodedJWT = JWTUtils.verify(token);
        String rolename = decodedJWT.getClaim("rolename").asString();
        return rolename;
    }

    /**
     * 获取真名
     * @param request
     * @return
     */
    public static String getTokenRealName(HttpServletRequest request) {
        //从请求头中获取token
        String token = request.getHeader("token");
        //验证token
        DecodedJWT decodedJWT = JWTUtils.verify(token);
        String realname = decodedJWT.getClaim("realname").asString();
        return realname;
    }

    /**
     * 获取消息推送状态
     * @param request
     * @return
     */
    public static String getTokenMsgFlag(HttpServletRequest request){
        //从请求头中获取token
        String token = request.getHeader("token");
        //验证token
        DecodedJWT decodedJWT = JWTUtils.verify(token);
        //获取消息推送状态
        String msgflag = decodedJWT.getClaim("msgflag").asString();
        return msgflag;
    }
}