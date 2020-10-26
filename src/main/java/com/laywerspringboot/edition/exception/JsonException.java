package com.laywerspringboot.edition.exception;

/**
 * @Author:小七
 * @createTime:2020-10-22-11-39
 */
public class JsonException extends RuntimeException {
    private String msg;
    public JsonException() {
    }
    public JsonException(String msg){
        super(msg);
    }
}
