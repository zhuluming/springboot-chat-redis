package com.laywerspringboot.edition.exception;

/**
 * @Author:小七
 * @createTime:2020-10-25-14-36
 */
public class SendMessageException extends RuntimeException {
    private String msg;
    public SendMessageException() {
    }
    public SendMessageException(String msg){
        super(msg);
    }

}
