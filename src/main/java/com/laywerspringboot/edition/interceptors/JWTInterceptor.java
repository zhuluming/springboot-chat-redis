package com.laywerspringboot.edition.interceptors;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.laywerspringboot.edition.Utils.JWTUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author:小七
 * @createTime:2020-10-26-22-03
 */
public class JWTInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //获取请求头中令牌
        String token = request.getHeader("token");
        try {
            JWTUtils.verify(token);//验证令牌
            return true;//放行请求
        } catch (SignatureVerificationException e) {
            throw new RuntimeException("无效签名!");
        }catch (TokenExpiredException e){
            throw new RuntimeException("token过期!");
        }catch (AlgorithmMismatchException e){
            throw new RuntimeException("token算法不一致!");
        }catch (Exception e){
            throw new RuntimeException("token无效!!");
        }

    }
}
