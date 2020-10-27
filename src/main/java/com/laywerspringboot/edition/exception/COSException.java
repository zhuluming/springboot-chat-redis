package com.laywerspringboot.edition.exception;

/**
 * @Author:小七
 * @createTime:2020-10-26-20-30
 */
public class COSException extends RuntimeException {
    private String msg;
    public COSException() {
    }
    public COSException(String msg){
        super(msg);
    }
}
