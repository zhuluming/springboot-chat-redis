/*
package com.laywerspringboot.edition;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Serializable;

*/
/**
 * @Author:小七
 * @createTime:2020-10-31-14-19
 *//*

@RunWith(SpringRunner.class)
public class RedisTest extends UserTest{
    @Autowired
    private RedisTemplate<String, String> strRedisTemplate;
    @Autowired
    private RedisTemplate<String, Serializable> serializableRedisTemplate;

    @Test
    public void testString() {
        strRedisTemplate.opsForValue().set("strKey", "zwqh");
        System.out.println(strRedisTemplate.opsForValue().get("strKey"));
    }
*/
/*

    @Test
    public void testSerializable() {
        User user=new User();
        user.setId(1);
        user.setUsername("朝雾轻寒");

         RedisUtils.set("user1", user);
        User user1 = (User) RedisUtils.get("user1");
        System.out.println("user:"+user1.getId()+","+user1.getUsername());
    }
*//*



}
*/
