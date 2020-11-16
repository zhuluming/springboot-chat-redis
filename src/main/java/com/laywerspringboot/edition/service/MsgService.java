package com.laywerspringboot.edition.service;

import com.laywerspringboot.edition.Utils.R;
import com.laywerspringboot.edition.entity.Msg;
import com.laywerspringboot.edition.entity.dto.MsgDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * (Msg)表服务接口
 *
 * @author makejava
 * @since 2020-11-14 22:07:28
 */
public interface MsgService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Msg queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Msg> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param msg 实例对象
     * @return 实例对象
     */
    Msg insert(Msg msg);

    /**
     * 修改数据
     *
     * @param msg 实例对象
     * @return 实例对象
     */
    Msg update(Msg msg);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);
    /**
     * 根据名字查询是否在线
     * @param name
     * @return
     */
    Msg queryByName(String name);
    /**
     * 用户发消息之后存信息
     * @param msg
     * @param key
     */
     void saveMsgToRedis(MsgDto msg, String key, String keyflag, Long time);

    /**
     * 用户不在时候存信息
     * @param msg
     * @param key
     * @param time
     * @param status
     */
    void saveMsgToRedis(MsgDto msg, String key,String keyflag,Long time,int status);

    /**
     * 给用户传记录
     * @param name  对方用户
     * @param tokenRealName 用户
     * @return
     */
     R getR(String name, String tokenRealName);

    /**
     * 获取消息
     * @param name
     * @param tokenRealName
     * @return
     */
   ArrayList<String> getMsgs(String name, String tokenRealName);

    /**
     * 获取铃铛消息
     * @param name  对方用户
     * @param tokenRealName 用户
     * @return
     */
    R getNotice( String name, String tokenRealName);
    /**
     * 获取铃铛消息和消息状态，已读1，未读0
     * @param name
     * @param tokenRealName
     * @return
     */
    ArrayList<Map<Object,Object>> getNoticeMsg(String name, String tokenRealName);
    /**
     * 获取铃铛消息和消息状态，已读1，未读0
     * @param name
     * @param tokenRealName
     * @return
     */
    void updateFlag(String name, String tokenRealName,String time);

    /**
     * 判断这个key中是否有消息
     * @param key
     * @return
     */
    boolean isNull(String key);
}