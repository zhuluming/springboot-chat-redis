package com.laywerspringboot.edition.exception;

import com.laywerspringboot.edition.Utils.R;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author:小七
 * @createTime:2020-10-24-22-48
 */
@RestControllerAdvice
public class MyExceptionHandler {
    @ExceptionHandler(JsonException.class)
    public R jsonException(Exception e){
        System.out.println("message:"+e.getMessage());
        return R.error(e.getMessage());
    }
    @ExceptionHandler(UserInfoException.class)
    public R userInfoException(Exception e){
        System.out.println("message:"+e.getMessage());
        return R.error(e.getMessage());
    }
    @ExceptionHandler(SendMessageException.class)
    public R sendMessageException(Exception e){
        System.out.println("message:"+e.getMessage());
        return R.error(e.getMessage());
    }
    @ExceptionHandler(Exception.class)
    public R exception(Exception e){
        System.out.println("message:"+e.getMessage());
        System.out.println("LocalizedMessage:"+e.getLocalizedMessage());
        e.printStackTrace();
        return R.error(e.getMessage());
    }
}
