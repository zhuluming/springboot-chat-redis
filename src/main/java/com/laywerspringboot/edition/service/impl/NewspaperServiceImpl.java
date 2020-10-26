package com.laywerspringboot.edition.service.impl;

import com.laywerspringboot.edition.dao.NewspaperDao;
import com.laywerspringboot.edition.entity.Newspaper;
import com.laywerspringboot.edition.service.NewspaperService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Newspaper)表服务实现类
 *
 * @author makejava
 * @since 2020-10-24 11:09:23
 */
@Service("newspaperService")
public class NewspaperServiceImpl implements NewspaperService {
    @Resource
    private NewspaperDao newspaperDao;

    /**
     * 通过ID查询单条数据
     *
     * @param pId 主键
     * @return 实例对象
     */
    @Override
    public Newspaper queryById(Integer pId) {
        return this.newspaperDao.queryById(pId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Newspaper> queryAllByLimit(int offset, int limit) {
        return this.newspaperDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param newspaper 实例对象
     * @return 实例对象
     */
    @Override
    public Newspaper insert(Newspaper newspaper) {
        this.newspaperDao.insert(newspaper);
        return newspaper;
    }

    /**
     * 修改数据
     *
     * @param newspaper 实例对象
     * @return 实例对象
     */
    @Override
    public Newspaper update(Newspaper newspaper) {
        this.newspaperDao.update(newspaper);
        return this.queryById(newspaper.getPId());
    }

    /**
     * 通过主键删除数据
     *
     * @param pId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer pId) {
        return this.newspaperDao.deleteById(pId) > 0;
    }
}