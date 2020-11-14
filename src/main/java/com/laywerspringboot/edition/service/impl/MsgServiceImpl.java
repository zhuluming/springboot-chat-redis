package com.laywerspringboot.edition.service.impl;

import com.laywerspringboot.edition.dao.MsgDao;
import com.laywerspringboot.edition.entity.Msg;
import com.laywerspringboot.edition.service.MsgService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


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
}