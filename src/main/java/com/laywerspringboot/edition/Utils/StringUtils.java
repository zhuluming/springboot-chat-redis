package com.laywerspringboot.edition.Utils;

/**
 * @Author:小七
 * @createTime:2020-10-23-20-04
 */
public class StringUtils {
    /**
     * 判断字符串是否为空
     * @param arg
     * @return 如果为空返回true
     */
    public static boolean isEmpty(String arg){
        return arg == null || arg.trim().equals("");
    }

}
