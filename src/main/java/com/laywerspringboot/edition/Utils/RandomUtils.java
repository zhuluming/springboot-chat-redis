package com.laywerspringboot.edition.Utils;

import java.util.Random;

/**
 * @Author:小七
 * @createTime:2020-10-24-16-03
 *
 */
public class RandomUtils {
    /**
     * 获取随机字符串
     * 获取salt的hashcode来截取
     * @return
     */
    public static int getPhoneUuid(){
        return  (int)(Math.random()*8998)+1000+1;
    }

    /**
     * 根据毫秒数获取随机数
     * @return
     */
    public static int getRandom(){
        long currentTimeMillis = System.currentTimeMillis();

        int i = new Random(currentTimeMillis).nextInt();
        int abs = Math.abs(i);


        return abs;
    }
    /**
     * 生成salt的静态方法,效率过慢
     * @param
     * @return
     */
    public static String getSalt(int n){
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz01234567890!@#$%^&*()".toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1+n; i++) {
            char aChar = chars[new Random().nextInt(chars.length)];
            sb.append(aChar);
        }
        return sb.toString();
    }

    /**
     * 获取随机位数
     * @return
     */
    public static int getRightRandom(){
        int random = new Random().nextInt(13);
        return random;
    }
    /**
     * 取一个字节b的16位的低(16-length)bit
     * @param b
     * @param length
     * @return
     */
    public static int getRightNum(int b,int length) {
        int mv = (0Xffff>>(16-length));
        return b&mv;
    }


}
