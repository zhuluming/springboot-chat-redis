package com.laywerspringboot.edition.exception;

import com.laywerspringboot.edition.Utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author:小七
 * @createTime:2020-10-24-22-48
 */
@RestControllerAdvice
@Slf4j
public class MyExceptionHandler {
    @ExceptionHandler(JsonException.class)
    public R jsonException(Exception e){
        log.debug(e.getMessage());
        return R.error("格式有误哦");
    }
    @ExceptionHandler(UserInfoException.class)
    public R userInfoException(Exception e){

        log.debug(e.getMessage());
        return R.error("用户信息有误");
    }
    @ExceptionHandler(SendMessageException.class)
    public R sendMessageException(Exception e){

        log.debug(e.getMessage());
        return R.error("发消息有误");
    }
    @ExceptionHandler(Exception.class)
    public R exception(Exception e){
        log.debug(e.getMessage());
        return R.error("出错了哦，请联系管理员");
    }
}
