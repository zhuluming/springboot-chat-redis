package com.laywerspringboot.edition.service.impl;

import com.laywerspringboot.edition.dao.UserroleDao;
import com.laywerspringboot.edition.entity.Userrole;
import com.laywerspringboot.edition.service.UserroleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;



/**
 * (Userrole)表服务实现类
 *
 * @author makejava
 * @since 2020-10-24 11:09:25
 */
@Service("userroleService")
public class UserroleServiceImpl implements UserroleService {
    @Resource
    private UserroleDao userroleDao;

    /**
     * 通过ID查询单条数据
     *
     * @param Id 主键
     * @return 实例对象
     */
    @Override
    public Userrole queryById(Integer Id) {
        return this.userroleDao.queryById(Id);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param uId 用户id
     * @return 实例对象
     */
    @Override
    public Userrole queryByUId(Integer uId) {
        return this.userroleDao.queryByUId(uId);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param rId 用户角色id
     * @return 实例对象
     */
    @Override
    public Userrole queryByRId(Integer rId) {
        return this.userroleDao.queryByRId(rId);
    }


    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Userrole> queryAllByLimit(int offset, int limit) {
        return this.userroleDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param userrole 实例对象
     * @return 实例对象
     */
    @Override
    public Userrole insert(Userrole userrole) {
        this.userroleDao.insert(userrole);
        return userrole;
    }

    /**
     * 修改数据
     *
     * @param userrole 实例对象
     * @return 实例对象
     */
    @Override
    public Userrole update(Userrole userrole) {
        this.userroleDao.update(userrole);
        return this.queryById(userrole.getUId());
    }

    /**
     * 通过主键删除数据
     *
     * @param uId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer uId) {
        return this.userroleDao.deleteById(uId) > 0;
    }
}