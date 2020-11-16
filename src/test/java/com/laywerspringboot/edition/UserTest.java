//package com.laywerspringboot.edition;
//
//import com.laywerspringboot.edition.constant.MsgConstant;
//import com.laywerspringboot.edition.dao.SearchDtoDao;
//import com.laywerspringboot.edition.dao.UserDao;
//import com.laywerspringboot.edition.entity.dto.MsgDto;
//import org.junit.jupiter.api.Test;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Repository;
//
//import javax.annotation.Resource;
//
///**
// * @Author:小七
// * @createTime:2020-10-23-22-07
// */
//
//
//@SpringBootTest
//@MapperScan(annotationClass = Repository.class,basePackages = "com.laywerspringboot.edition.dao")
//public class UserTest  {
//    @Resource
//    private UserDao userDao;
//    @Resource
//    private SearchDtoDao searchDto;
//    @Autowired
//    private RedisTemplate redisTemplate;
//
//    @Test
//    public void testUserSelect(){
//       /* Integer integer = 1;
//        System.out.println(userDao);
//        User user = userDao.queryById(integer);*/
//        /*User user = new User();
//        user.setPhoneid("13657067872");
//        userDao.insert(user);
//        System.out.println(userDao.queryAll(user));*/
//     /*   System.out.println(searchDto.SearchByCaseID("asdas", "1"));
//        System.out.println(searchDto.SearchByLaywerName("asdas").size());
//        System.out.println(searchDto.SearchByParty("asdas", "dasd"));*/
//        //redisTemplate.opsForValue().set("123", "23123");
//        MsgDto msgDto = new MsgDto();
//        msgDto.setRole("sdasda");
//        redisTemplate.opsForValue().set(MsgConstant.USER_PREFIX+"sanqi", msgDto);
//        //redisTemplate.opsForHash().put(MsgConstant.USER_PREFIX+"三七", "qwe", "wewertwertsrt");
//        //String o = (String) redisTemplate.opsForHash().get(MsgConstant.USER_PREFIX + "三七", "qwe");
//        //System.out.println(o);
//        //redisTemplate.opsForHash().get(MsgConstant.USER_PREFIX+"三七", "qwe");
//        //System.out.println(redisTemplate.hasKey(MsgConstant.USER_PREFIX + "xiaoqi"));
//    }
//}
