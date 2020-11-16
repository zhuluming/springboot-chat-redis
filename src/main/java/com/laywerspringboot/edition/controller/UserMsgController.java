
package com.laywerspringboot.edition.controller;

import com.laywerspringboot.edition.Utils.JWTUtils;
import com.laywerspringboot.edition.Utils.R;
import com.laywerspringboot.edition.constant.MsgConstant;
import com.laywerspringboot.edition.entity.Cases;
import com.laywerspringboot.edition.entity.Msg;
import com.laywerspringboot.edition.entity.dto.MsgDto;
import com.laywerspringboot.edition.entity.dto.MsgFlag;
import com.laywerspringboot.edition.exception.UserInfoException;
import com.laywerspringboot.edition.service.CasesService;
import com.laywerspringboot.edition.service.MsgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Set;

/**
 * @Author:小七
 * @createTime:2020-11-08-15-27
 */
@CrossOrigin()
@RestController
@RequestMapping("userChat")
@Api(description = "聊天模块api",value = "聊天")
public class UserMsgController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private CasesService casesService;
    @Autowired
    private MsgService msgService;
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
    @PostMapping("/sendMsg")
    @CrossOrigin()
    @ApiOperation(value = "发送消息")
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
        //设置存消息状态的前缀
        //当前用户
        String keyflag = MsgConstant.USER_PREFIX+tokenRealName+msg.getToName();
        //对方用户
        String key1flag = MsgConstant.USER_PREFIX+msg.getToName()+tokenRealName;
        //前缀加用户真美+to+发送对象
        String key = MsgConstant.USER_PREFIX+tokenRealName+MsgConstant.CHAT_TO_PREFIX+msg.getToName();
        //定义时间
        Long time = System.currentTimeMillis();
        //存消息状态
        // System.out.println(msg.getMsg());
        //System.out.println(msg.getToName());
        //System.out.println(key1flag);
        //System.out.println("==================");
        msgService.saveMsgToRedis(msg, key,keyflag,time);
        ///String key1 = MsgConstant.USER_PREFIX+msg.getToName()+MsgConstant.CHAT_TO_PREFIX+tokenRealName;
        //存消息
        redisTemplate.opsForHash().put(key,time,msg.getMsg());
        ///redisTemplate.opsForHash().put(key1,time,msg.getMsg());

        ///msg.setUserName(msg.getToName());
        ///msg.setToName(tokenRealName);
        //存状态
        ///saveMsgToRedis(msg,key1,key1flag,time);
        //调一下前端的接口

        //再把消息发送给对面
     /*   String forObject = restTemplate.getForObject("http://127.0.0.1:8080/chat/reply/{msg}/name/{toName}/sendName/{tokenRealName}",
                String.class, msg.getMsg(), msg.getToName(),tokenRealName);*/



        /**
         * 信息回显
         */
        return R.replyOk(msg.getMsg());
    }

    //todo 读的时候把所有聊过天的对象名传给前端，然后点击对象时候，对象不在线，或者搜索不存在，要给个提示
    @CrossOrigin()
    @GetMapping("/login")
    @ApiOperation(value = "登录")
    public R login(HttpServletRequest request){
        String tokenRealName = JWTUtils.getTokenRealName(request);
        Msg msg = msgService.queryByName(tokenRealName);
        if (msg == null){
            Msg msg1 = new Msg();
            msg1.setName(tokenRealName);
            msg1.setState("1");
            msgService.insert(msg1);
        }
        String key1 = MsgConstant.USER_PREFIX+tokenRealName+MsgConstant.REDIS_MATCH_PREFIX;
        Set<String> keys = redisTemplate.keys(key1);
        ArrayList<String> list = new ArrayList<>();

        for (String key : keys) {
            //判断是否是要的数据
            if (key.indexOf(MsgConstant.CHAT_TO_PREFIX) == -1){
                if (msgService.isNull(key)){

                    list.add(key.split(MsgConstant.USER_PREFIX+tokenRealName)[1]);
                }
            }
            //map.put(MsgConstant.USER_PREFIX+tokenRealName+key,key.split(MsgConstant.USER_PREFIX+tokenRealName+MsgConstant.REDIS_MATCH_PREFIX)[1]);
        }
        return R.loginOk("这是之前的聊天对象").put("name", list);

    }




    /**
     * 用户在线时
     * @param toName 对方用户
     * @param request
     * @return
     */
    @CrossOrigin()
    @GetMapping("/pull/{toName}")
    @ApiOperation(value = "拉取消息")
    public R reply(@ApiParam("对方名字")@PathVariable("toName") String toName, HttpServletRequest request){
        //本人名
        String tokenRealName = JWTUtils.getTokenRealName(request);
        //前缀加用户真名
        String key = MsgConstant.USER_PREFIX+toName+tokenRealName;
        // todo 测试
        //System.out.println(key);
        //System.out.println("===============");
        MsgFlag o = (MsgFlag) redisTemplate.opsForValue().get(key);
        //如果在线

        if (o.getStatus() == 1){
            return msgService.getR(toName, tokenRealName);
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
    @CrossOrigin()
    @GetMapping("/notice/{toName}")
    @ApiOperation(value = "铃铛接口")
    public R notice(@ApiParam("对方名字")@PathVariable("toName") String toName,HttpServletRequest request){
        //本人名
        String tokenRealName = JWTUtils.getTokenRealName(request);
        //前缀加用户真名
        String key = MsgConstant.USER_PREFIX+tokenRealName+toName;
        MsgFlag o = (MsgFlag) redisTemplate.opsForValue().get(key);
        return msgService.getNotice(toName, tokenRealName);
    }



    /**
     * 从用户列表中搜索用户
     * 返回用户要聊天对象的状态
     * @param name 用户想聊天的对象的名字
     * @param request
     * @return
     */
    @CrossOrigin()
    @GetMapping("/search/{name}")
    @ApiOperation(value = "搜索用户")
    public R search(@ApiParam("对方名字")@PathVariable("name")String name,HttpServletRequest request){
        //获取用户的名字
        String tokenRealName = JWTUtils.getTokenRealName(request);
        //生成状态

        Msg msg2 = new Msg();
        try {
            msg2 = msgService.queryByName(name);
        }catch (Exception e){
            throw new UserInfoException("请不要找不存在的用户聊天哦!");
        }
        // System.out.println(name);
        if (tokenRealName.equals(name)){
            return R.error("请不要自己找自己聊天");
        }
        //从案件列表中搜索
        Cases cases = casesService.queryByParty(tokenRealName);
        String tokenRole = JWTUtils.getTokenRole(request);
        /**
         * 判断角色
         * 法官和管理员可以自由互撩
         */
        if (tokenRole.equals("用户")){
            if (cases.getAdmin().equals(name)){
                return msgService.getR(name, tokenRealName).put("state", msg2.getState());

            }
            else if (cases.getLawyer().equals(name)){
                return msgService.getR(name, tokenRealName).put("state", msg2.getState());
            }
            else {
                return R.isError(0,"请不要骚扰其他无关用户哦");
            }
        }

        return msgService.getR(name, tokenRealName).put("state", msg2.getState());
    }


    /**
     * 用户退出界面时
     */
    @CrossOrigin()
    @GetMapping("/exit")
    @ApiOperation(value = "用户退出")
    public void exit( HttpServletRequest request){
        String tokenRealName = JWTUtils.getTokenRealName(request);
        String key1 = MsgConstant.USER_PREFIX+tokenRealName+MsgConstant.REDIS_MATCH_PREFIX;
        Set<String> keys = redisTemplate.keys(key1);
        for (String key : keys) {
            MsgFlag o = (MsgFlag) redisTemplate.opsForValue().get(key);
            o.setStatus(0);
            redisTemplate.opsForValue().set(key, o);
        }
        Msg msg = msgService.queryByName(tokenRealName);
        msg.setState("0");
        msgService.update(msg);
    }

    /**
     * 用户退出界面时
     */
    @CrossOrigin()
    @GetMapping("/updateMagFlag/{time}/toName/{toName}")
    @ApiOperation(value = "确定消息是否已读")
    public void updateMagFlag(@PathVariable("time")String time,@PathVariable("toName")String toName, HttpServletRequest request){
        //本人名
        String tokenRealName = JWTUtils.getTokenRealName(request);
        //前缀加用户真名
        System.out.println(time);
        System.out.println(toName);
        String key = MsgConstant.USER_PREFIX+tokenRealName+toName;
        MsgFlag o = (MsgFlag) redisTemplate.opsForValue().get(key);
        msgService.updateFlag(toName, tokenRealName,time);
    }












}
