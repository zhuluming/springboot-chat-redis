package com.laywerspringboot.edition.service.impl;

import com.laywerspringboot.edition.dao.NoticeDao;
import com.laywerspringboot.edition.entity.Notice;
import com.laywerspringboot.edition.service.NoticeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * (Notice)表服务实现类
 *
 * @author makejava
 * @since 2020-10-24 11:09:24
 */
@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {
    @Resource
    private NoticeDao noticeDao;

    /**
     * 通过ID查询单条数据
     *
     * @param nId 主键
     * @return 实例对象
     */
    @Override
    public Notice queryById(Integer nId) {
        return this.noticeDao.queryById(nId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Notice> queryAllByLimit(int offset, int limit) {
        return this.noticeDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param notice 实例对象
     * @return 实例对象
     */
    @Override
    public Notice insert(Notice notice) {
        this.noticeDao.insert(notice);
        return notice;
    }

    /**
     * 修改数据
     *
     * @param notice 实例对象
     * @return 实例对象
     */
    @Override
    public Notice update(Notice notice) {
        this.noticeDao.update(notice);
        return this.queryById(notice.getNId());
    }

    /**
     * 通过主键删除数据
     *
     * @param nId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer nId) {
        return this.noticeDao.deleteById(nId) > 0;
    }
}