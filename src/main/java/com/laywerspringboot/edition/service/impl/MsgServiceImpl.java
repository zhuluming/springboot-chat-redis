package com.laywerspringboot.edition.service.impl;

import com.laywerspringboot.edition.Utils.R;
import com.laywerspringboot.edition.constant.MsgConstant;
import com.laywerspringboot.edition.dao.MsgDao;
import com.laywerspringboot.edition.entity.Msg;
import com.laywerspringboot.edition.entity.dto.MsgDto;
import com.laywerspringboot.edition.entity.dto.MsgFlag;
import com.laywerspringboot.edition.service.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * (Msg)表服务实现类
 *
 * @author makejava
 * @since 2020-11-14 22:07:29
 */
@Service("msgService")
public class MsgServiceImpl implements MsgService {
    @Resource
    private MsgDao msgDao;
    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Msg queryById(Integer id) {
        return this.msgDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Msg> queryAllByLimit(int offset, int limit) {
        return this.msgDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param msg 实例对象
     * @return 实例对象
     */
    @Override
    public Msg insert(Msg msg) {
        this.msgDao.insert(msg);
        return msg;
    }

    /**
     * 修改数据
     *
     * @param msg 实例对象
     * @return 实例对象
     */
    @Override
    public Msg update(Msg msg) {
        this.msgDao.update(msg);
        return this.queryById(msg.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.msgDao.deleteById(id) > 0;
    }

    @Override
    public Msg queryByName(String name) {
        return this.msgDao.queryByName(name);
    }
    /**
     * 用户发消息之后存信息
     * @param msg
     * @param key
     */
    public void saveMsgToRedis(MsgDto msg, String key, String keyflag, Long time) {
        //存信息和信息表示状态
        redisTemplate.opsForHash().put(key,time,msg.getMsg());

        MsgFlag msgFlag = (MsgFlag) redisTemplate.opsForValue().get(keyflag);
        if (msgFlag.getTimes() == null){
            ArrayList<Long> longs = new ArrayList<>();
            longs.add(time);
            msgFlag.setTimes(longs);
        }
        if (msgFlag.getFlag() == null){
            ArrayList<Integer> list = new ArrayList<>();
            list.add(0);
            msgFlag.setFlag(list);
        }
        msgFlag.getTimes().add(time);
        msgFlag.getFlag().add(0);
        //前缀加用户真名,用来存用户的标值及定位信息的工具
        msgFlag.setKey(key);
        msgFlag.setStatus(1);
        redisTemplate.opsForValue().set(keyflag, msgFlag);

    }

    /**
     * 用户不在时候存信息
     * @param msg
     * @param key
     * @param time
     * @param status
     */
    public void saveMsgToRedis(MsgDto msg, String key,String keyflag,Long time,int status) {
        //存信息和信息表示状态
        redisTemplate.opsForHash().put(key,time,msg.getMsg());

        MsgFlag msgFlag = (MsgFlag) redisTemplate.opsForValue().get(keyflag);
        msgFlag.getTimes().add(time);
        msgFlag.getFlag().add(0);
        //前缀加用户真名,用来存用户的标值及定位信息的工具
        msgFlag.setKey(key);
        msgFlag.setStatus(status);
        redisTemplate.opsForValue().set(keyflag, msgFlag);

    }

    /**
     * 给用户传记录
     * @param name  对方用户
     * @param tokenRealName 用户
     * @return
     */
    public R getR(String name, String tokenRealName) {
        if (redisTemplate.hasKey(MsgConstant.USER_PREFIX+tokenRealName+MsgConstant.CHAT_TO_PREFIX+name)){
            //之前存在过聊天
            //把之前消息都拉出来
            //发送方
            ArrayList<String> list = getMsgs(name, tokenRealName);
            //todo 测试
          /*  for (String s : list) {
              //  System.out.println(s);
            }*/
            //接收方的所有消息
            ArrayList<String> list1 = getMsgs(tokenRealName, name);
            //todo 测试
            //System.out.println("===============");
    /*        for (String s : list1) {
              //  System.out.println(s);
            }*/
            return R.findOk(tokenRealName).put(tokenRealName, list).put(name, list1);
        }
        else{
            //建立聊天记录前缀
            //times 用来记录每一条记录的时间
            MsgFlag msgFlag = new MsgFlag();
            MsgFlag msgFlag1 = new MsgFlag();
            msgFlag.setStatus(1);
            msgFlag1.setStatus(1);
            redisTemplate.opsForValue().set(MsgConstant.USER_PREFIX+tokenRealName+name, msgFlag);
            redisTemplate.opsForValue().set(MsgConstant.USER_PREFIX+name+tokenRealName, msgFlag1);
            return R.findOk("恭喜开始第一次聊天").put("name", name);
        }
    }

    /**
     * 获取消息
     * @param name
     * @param tokenRealName
     * @return
     */
    public ArrayList<String> getMsgs(String name, String tokenRealName) {
        MsgFlag msg = (MsgFlag) redisTemplate.opsForValue().get(MsgConstant.USER_PREFIX + tokenRealName+name );
        //todo 测试
        // System.out.println(MsgConstant.USER_PREFIX + tokenRealName+name);
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < msg.getTimes().size(); i++) {
            Long time = msg.getTimes().get(i);
            String message = (String) redisTemplate.opsForHash().get(MsgConstant.USER_PREFIX + tokenRealName + MsgConstant.CHAT_TO_PREFIX + name, time);
            // System.out.println(MsgConstant.USER_PREFIX + tokenRealName + MsgConstant.CHAT_TO_PREFIX + name);
            msg.getFlag().set(i, 0);
            //todo 测试
            //  System.out.println("===============");
            // System.out.println(message);
            list.add(message);
        }
        redisTemplate.opsForValue().set(MsgConstant.USER_PREFIX + tokenRealName+name, msg);
      /*
        for (Long time : msg.getTimes()) {
            String message = (String) redisTemplate.opsForHash().get(MsgConstant.USER_PREFIX + tokenRealName + MsgConstant.CHAT_TO_PREFIX + name, time);
            list.add(message);
        }*/
        return list;
    }

    /**
     * 获取铃铛消息
     * @param name  对方用户
     * @param tokenRealName 用户
     * @return
     */
    public R getNotice( String name, String tokenRealName) {
        if (redisTemplate.hasKey(MsgConstant.USER_PREFIX+tokenRealName+MsgConstant.CHAT_TO_PREFIX+name)){
            //之前存在过聊天
            //把之前消息都拉出来
            //发送方
            ArrayList<Map<Object, Object>> list = getNoticeMsg(name, tokenRealName);
            //todo 测试
          /*  for (String s : list) {
              //  System.out.println(s);
            }*/
            //接收方的所有消息
            ArrayList<Map<Object, Object>> list1 = getNoticeMsg(tokenRealName, name);
            //todo 测试
            //System.out.println("===============");
    /*        for (String s : list1) {
              //  System.out.println(s);
            }*/
            return R.findOk(tokenRealName).put(tokenRealName, list).put(name, list1);
        }
        else{
            //建立聊天记录前缀
            //times 用来记录每一条记录的时间
            MsgFlag msgFlag = new MsgFlag();
            MsgFlag msgFlag1 = new MsgFlag();
            msgFlag.setStatus(1);
            msgFlag1.setStatus(1);
            redisTemplate.opsForValue().set(MsgConstant.USER_PREFIX+tokenRealName+name, msgFlag);
            redisTemplate.opsForValue().set(MsgConstant.USER_PREFIX+name+tokenRealName, msgFlag1);
            return R.findOk("恭喜开始第一次聊天").put("name", name);
        }
    }

    /**
     * 获取铃铛消息和消息状态，已读1，未读0
     * @param name
     * @param tokenRealName
     * @return
     */
    public ArrayList<Map<Object,Object>> getNoticeMsg(String name, String tokenRealName) {
        MsgFlag msg = (MsgFlag) redisTemplate.opsForValue().get(MsgConstant.USER_PREFIX + tokenRealName+name );
        //todo 测试
        // System.out.println(MsgConstant.USER_PREFIX + tokenRealName+name);
        ArrayList<Map<Object,Object>> list = new ArrayList<>();
        for (int i = 0; i < msg.getTimes().size(); i++) {
            HashMap<Object, Object> map = new HashMap<>();
            Long time = msg.getTimes().get(i);
            //System.out.println("=--------------------"+time);
            String message = (String) redisTemplate.opsForHash().get(MsgConstant.USER_PREFIX + tokenRealName + MsgConstant.CHAT_TO_PREFIX + name, time);
            // System.out.println(MsgConstant.USER_PREFIX + tokenRealName + MsgConstant.CHAT_TO_PREFIX + name);
            msg.getFlag().set(i, 0);
            //Date date = new Date(time);
            //System.out.println("----------------"+date);
            //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd:HH-mm-ss");
            //String format = simpleDateFormat.format(date);
            map.put("msg", message);
            map.put("time", time);
            map.put("flag", msg.getFlag().get(i));
            list.add(map);
        }
        redisTemplate.opsForValue().set(MsgConstant.USER_PREFIX + tokenRealName+name, msg);
      /*
        for (Long time : msg.getTimes()) {
            String message = (String) redisTemplate.opsForHash().get(MsgConstant.USER_PREFIX + tokenRealName + MsgConstant.CHAT_TO_PREFIX + name, time);
            list.add(message);
        }*/
        return list;
    }

    /**
     * 获取铃铛消息和消息状态，已读1，未读0
     * @param name
     * @param tokenRealName
     * @return
     */
    public void updateFlag(String name, String tokenRealName,String time)  {
        try {
            MsgFlag msg = (MsgFlag) redisTemplate.opsForValue().get(MsgConstant.USER_PREFIX + tokenRealName+name );
            //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd:HH-mm-ss");
            //Date parse = simpleDateFormat.parse(time);
            //long time1 = parse.getTime();

           // System.out.println("========="+time1);
            //System.out.println("========="+parse);
            int flag = msg.getTimes().indexOf(time);
            msg.getFlag().set(flag, 1);
            redisTemplate.opsForValue().set(MsgConstant.USER_PREFIX + tokenRealName+name, msg);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
      /*
        for (Long time : msg.getTimes()) {
            String message = (String) redisTemplate.opsForHash().get(MsgConstant.USER_PREFIX + tokenRealName + MsgConstant.CHAT_TO_PREFIX + name, time);
            list.add(message);
        }*/

    }

    @Override
    public boolean isNull(String key) {
        MsgFlag msg = (MsgFlag) redisTemplate.opsForValue().get(key);
        if (msg != null && msg.getFlag().size() != 0 || msg.getTimes().size() != 0){
            return true;
        }

        return false;
    }
}