package com.laywerspringboot.edition;

import com.laywerspringboot.edition.dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @Author:小七
 * @createTime:2020-10-23-22-07
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@MapperScan(annotationClass = Repository.class,basePackages = "com.laywerspringboot.edition.dao")
public class UserTest  {
    @Resource
    private UserDao userDao;
    @Test
    public void testUserSelect(){
       /* Integer integer = 1;
        System.out.println(userDao);
        User user = userDao.queryById(integer);*/
        /*User user = new User();
        user.setPhoneid("13657067872");
        userDao.insert(user);
        System.out.println(userDao.queryAll(user));*/
    }
}
