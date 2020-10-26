package com.laywerspringboot.edition.exception;

/**
 * @Author:小七
 * @createTime:2020-10-23-20-03
 */
public class UserInfoException extends RuntimeException {
    private String msg;
    public UserInfoException(){}
    public UserInfoException(String message) {
        super(message);

    }
}
