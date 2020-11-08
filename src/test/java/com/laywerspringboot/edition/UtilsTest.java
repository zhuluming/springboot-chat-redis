package com.laywerspringboot.edition;

import com.laywerspringboot.edition.Utils.ObjectUtils;
import com.laywerspringboot.edition.Utils.StringUtils;
import com.laywerspringboot.edition.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @Author:小七
 * @createTime:2020-10-23-20-07
 */
public class UtilsTest extends UserTest {
    @Test
    public void testUtils(){
        System.out.println(StringUtils.isEmpty(""));
        System.out.println(StringUtils.isEmpty(null));
    }
    @Test
    public void testObjUtils(){
        List<User> users = new ArrayList<>();
        Assert.isTrue(ObjectUtils.isEmpty(users));

    }
    @Test
    public void testRandomUtils(){
/*        int random = RandomUtils.getRandom();
        System.out.println(random);
        System.out.println("====================");
        String salt = RandomUtils.getSalt();
        System.out.println(salt);
        System.out.println("====================");
        int i = salt.hashCode();
        System.out.println(i);
        System.out.println("====================");
        int rightRandom = RandomUtils.getRightRandom();
        System.out.println(rightRandom);
        System.out.println("====================");
        int rightNum = RandomUtils.getRightNum(i, rightRandom);
        System.out.println(rightNum);
        System.out.println("====================");*/
        //8 9 -11
       /* for (int i = 0; i < 100; i++) {

            System.out.println(RandomUtils.getPhoneUuid());
        }*/
    }
    @Test
    public void testcurrentTime(){
       /* long currentTimeMillis = System.currentTimeMillis();
        System.out.println(currentTimeMillis);
        int a = (int) currentTimeMillis;
        System.out.println(a);*/
    }
    @Test
    public void testAutomic(){
     /*   Integer flag = 0;
        AtomicInteger atomicInteger = new AtomicInteger(flag);
        System.out.println(atomicInteger);
        for (int i = 0; i <10 ; i++) {
            atomicInteger.addAndGet(1);
        }
        System.out.println(atomicInteger);*/

    }
    @Test
    public void testDelete(){
        Long time = new Date().getTime();


        System.out.println(time);
       int i =45;
        System.out.println(666);
        Long time2 = new Date().getTime();
        System.out.println(time2);
        System.out.println(time2-time);
    }

}
