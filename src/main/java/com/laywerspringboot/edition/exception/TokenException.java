package com.laywerspringboot.edition.exception;

/**
 * @Author:小七
 * @createTime:2020-10-26-22-12
 */
public class TokenException extends RuntimeException {
    private String msg;
    public TokenException() {
    }
    public TokenException(String msg){
        super(msg);
    }
}
