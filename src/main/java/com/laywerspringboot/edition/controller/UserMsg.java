package com.laywerspringboot.edition.controller;

import com.laywerspringboot.edition.Utils.JWTUtils;
import com.laywerspringboot.edition.Utils.R;
import com.laywerspringboot.edition.constant.MsgConstant;
import com.laywerspringboot.edition.entity.Cases;
import com.laywerspringboot.edition.entity.dto.MsgDto;
import com.laywerspringboot.edition.entity.dto.MsgFlag;
import com.laywerspringboot.edition.service.CasesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Set;

/**
 * @Author:小七
 * @createTime:2020-11-08-15-27
 */
@RestController
@RequestMapping("userChat")
public class UserMsg {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private CasesService casesService;
    /**
     * 需要参数：
     * 1.是否开启消息推送，防止有消息返回时无小红点
     *  如果开启，则返回消息推送通知到用户界面,并把消息存入到redis
     * 2.用户如果没开启消息通知，则返回消息存储到redis
     * 3.消息有序，需要用一个队列来存，考虑并发则用阻塞队列blockingqueue,不考虑并发则用queue
     * 4.给一个用户是否停留在聊天室的表示，如果等于1，则表示用户在聊天室，如果不等于1，则表示用户已经退出
     * 5.要发送目标的名字
     * 6.可以在redis中将用户信息，用户是否停留在聊天室状态，用户发的消息全部封装成一个消息实体类
     *   再把消息实体类存储到redis中，这样可以判断对方是否在线
     * 7.redis中每次存的数据的key为前缀+用户的名字哦，用hash结构，filed为用户属性，value为用户名字
     * @param msg 用户每次发送的消息体，里面包用户信息，用户名字，用户身份，用户是否开启消息推送，发送对象的名字
     * @return 返回从消息接收方返回的消息
     */
    //前端轮询
    @GetMapping("/sendMsg")
    public R sendMsg(@RequestBody MsgDto msg, HttpServletRequest request){
        //1.校验token，并获取用户信息
        //获取用户的消息推送状态
        String tokenMsgFlag = JWTUtils.getTokenMsgFlag(request);
        msg.setStatus(tokenMsgFlag);
        //获取用户的名字
        String tokenRealName = JWTUtils.getTokenRealName(request);
        msg.setUserName(tokenRealName);
        //获取用户的身份，来判断是要和谁进行聊天
        String tokenRole = JWTUtils.getTokenRole(request);
        msg.setRole(tokenRole);
        //设置key
        //前缀加用户真美+to+发送对象
        String key = MsgConstant.USER_PREFIX+tokenRealName+MsgConstant.CHAT_TO_PREFIX+msg.getToName();
        //定义时间
        long time = System.currentTimeMillis();
        //存消息状态
        saveMsgToRedis(msg, key,time);
        String key1 = MsgConstant.USER_PREFIX+msg.getToName()+MsgConstant.CHAT_TO_PREFIX+tokenRealName;
        //存消息
        redisTemplate.opsForHash().put(key,time,msg.getMsg());
        redisTemplate.opsForHash().put(key1,time,msg.getMsg());

        msg.setUserName(msg.getToName());
        msg.setToName(tokenRealName);
        //存状态
        saveMsgToRedis(msg,key1,time);
        //调一下前端的接口

        //再把消息发送给对面
     /*   String forObject = restTemplate.getForObject("http://127.0.0.1:8080/chat/reply/{msg}/name/{toName}/sendName/{tokenRealName}",
                String.class, msg.getMsg(), msg.getToName(),tokenRealName);*/
        /**
         * 信息回显
         */
        return R.replyOk(msg.getMsg());
    }


    /**
     * 用户在线时
     * @param toName 对方用户
     * @param request
     * @return
     */
    @GetMapping("pull/{toName}")
    public R reply(@PathVariable("toName") String toName,HttpServletRequest request){
        //本人名
        String tokenRealName = JWTUtils.getTokenRealName(request);
        //前缀加用户真名
        String key = MsgConstant.USER_PREFIX+tokenRealName+MsgConstant.CHAT_TO_PREFIX+toName;
        MsgFlag o = (MsgFlag) redisTemplate.opsForValue().get(key);
        //如果在线
        if (o.getStatus() == 1){
            return getR(toName, tokenRealName);
        }else {
            //告诉前端，用户不在线，这时候前端去异步调用铃铛接口
            return R.error("用户不在线");
           /* //前缀+用户真名+to+发送过来的用户的名
            String key1 = MsgConstant.USER_PREFIX+toName+MsgConstant.CHAT_TO_PREFIX+tokenRealName;
            MsgDto msgDto = new MsgDto();
            msgDto.setMsg(msg);
            msgDto.setToName(tokenRealName);
            msgDto.setUserName(toName);
            long time = System.currentTimeMillis();
            int status = 0;
            saveMsgToRedis(msgDto, key1,time,status);
            return R.replyOk("用户目前不在线，已将消息发送给对方");*/
        }
    }

    /**
     * 给铃铛传消息
     * @return
     */
    @GetMapping("notice/{toName}")
    public R notice(@PathVariable("toName") String toName,HttpServletRequest request){
        //本人名
        String tokenRealName = JWTUtils.getTokenRealName(request);
        //前缀加用户真名
        String key = MsgConstant.USER_PREFIX+tokenRealName+MsgConstant.CHAT_TO_PREFIX+toName;
        MsgFlag o = (MsgFlag) redisTemplate.opsForValue().get(key);
        return getR(toName, tokenRealName);
    }



    /**
     * 从用户列表中搜索用户
     * 返回用户要聊天对象的状态
     * @param name 用户想聊天的对象的名字
     * @param request
     * @return
     */
    @GetMapping("/search/{name}")
    public R search(@PathVariable("name")String name,HttpServletRequest request){
        //获取用户的名字
        String tokenRealName = JWTUtils.getTokenRealName(request);
        if (tokenRealName.equals(name)){
            return R.error("请不要自己找自己聊天");
        }
        //从案件列表中搜索
        Cases cases = casesService.queryByParty(tokenRealName);
        if (cases.getAdmin().equals(name)){
            return getR(name, tokenRealName);

        }
        else if (cases.getLawyer().equals(name)){
            return getR(name, tokenRealName);
        }
        else {
          return R.error("请不要骚扰其他无关用户哦");
        }

    }


    /**
     * 用户退出界面时
     */
    @GetMapping("/exit")
    public void exit( HttpServletRequest request){
        String tokenRealName = JWTUtils.getTokenRealName(request);
        String key1 = MsgConstant.USER_PREFIX+tokenRealName+MsgConstant.REDIS_MATCH_PREFIX;
        Set<String> keys = redisTemplate.keys(key1);
        for (String key : keys) {
            MsgFlag o = (MsgFlag) redisTemplate.opsForValue().get(key);
            o.setStatus(0);
            redisTemplate.opsForValue().set(key, o);
        }

    }





    /**
     * 用户发消息之后存信息
     * @param msg
     * @param key
     */
    private void saveMsgToRedis(MsgDto msg, String key,Long time) {
        //存信息和信息表示状态
        redisTemplate.opsForHash().put(key,time,msg.getMsg());

        MsgFlag msgFlag = (MsgFlag) redisTemplate.opsForValue().get(key);
        msgFlag.getTimes().add(time);
        msgFlag.getFlag().add(0);
        //前缀加用户真名,用来存用户的标值及定位信息的工具
        msgFlag.setKey(key);
        msgFlag.setStatus(1);
        redisTemplate.opsForValue().set(key, msgFlag);

    }

    /**
     * 用户不在时候存信息
     * @param msg
     * @param key
     * @param time
     * @param status
     */
    private void saveMsgToRedis(MsgDto msg, String key,Long time,int status) {
        //存信息和信息表示状态
        redisTemplate.opsForHash().put(key,time,msg.getMsg());

        MsgFlag msgFlag = (MsgFlag) redisTemplate.opsForValue().get(key);
        msgFlag.getTimes().add(time);
        msgFlag.getFlag().add(0);
        //前缀加用户真名,用来存用户的标值及定位信息的工具
        msgFlag.setKey(key);
        msgFlag.setStatus(status);
        redisTemplate.opsForValue().set(key, msgFlag);

    }

    /**
     * 给用户传记录
     * @param name  对方用户
     * @param tokenRealName 用户
     * @return
     */
    private R getR( String name, String tokenRealName) {
        if (redisTemplate.hasKey(MsgConstant.USER_PREFIX+tokenRealName+MsgConstant.CHAT_TO_PREFIX+name)){
            //之前存在过聊天
            //把之前消息都拉出来
            //发送方
            ArrayList<String> list = getMsgs(name, tokenRealName);
            //接收方的所有消息
            ArrayList<String> list1 = getMsgs(tokenRealName, name);
            return R.findOk(tokenRealName).put(tokenRealName, list).put(name, list1);
        }
        else{
            //建立聊天记录前缀
            //times 用来记录每一条记录的时间
            MsgFlag msgFlag = new MsgFlag();
            MsgFlag msgFlag1 = new MsgFlag();
            msgFlag.setStatus(1);
            msgFlag1.setStatus(1);
            redisTemplate.opsForValue().set(MsgConstant.USER_PREFIX+tokenRealName+MsgConstant.CHAT_TO_PREFIX+name, msgFlag);
            redisTemplate.opsForValue().set(MsgConstant.USER_PREFIX+name+MsgConstant.CHAT_TO_PREFIX+tokenRealName, msgFlag1);
            return R.findOk("恭喜开始第一次聊天");
        }
    }

    /**
     * 获取消息
     * @param name
     * @param tokenRealName
     * @return
     */
    private ArrayList<String> getMsgs(String name, String tokenRealName) {
        MsgFlag msg = (MsgFlag) redisTemplate.opsForValue().get(MsgConstant.USER_PREFIX + tokenRealName + MsgConstant.CHAT_TO_PREFIX + name);
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < msg.getTimes().size(); i++) {
            Long time = msg.getTimes().get(i);
            String message = (String) redisTemplate.opsForHash().get(MsgConstant.USER_PREFIX + tokenRealName + MsgConstant.CHAT_TO_PREFIX + name, time);
            msg.getFlag().set(i, 0);
            list.add(message);
        }
        redisTemplate.opsForValue().set(MsgConstant.USER_PREFIX + tokenRealName + MsgConstant.CHAT_TO_PREFIX + name, msg);
      /*
        for (Long time : msg.getTimes()) {
            String message = (String) redisTemplate.opsForHash().get(MsgConstant.USER_PREFIX + tokenRealName + MsgConstant.CHAT_TO_PREFIX + name, time);
            list.add(message);
        }*/
        return list;
    }

}
