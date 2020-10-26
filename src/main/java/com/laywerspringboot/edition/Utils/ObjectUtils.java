package com.laywerspringboot.edition.Utils;

import java.util.List;

/**
 * @Author:小七
 * @createTime:2020-10-24-13-37
 */
public class ObjectUtils {
    /**
     * 判断集合是否为空
     * @param obj
     * @return 如果为空则返回true
     */
    public static boolean isEmpty(List obj ){
        return obj.size() == 0 || obj == null;
    }
}
