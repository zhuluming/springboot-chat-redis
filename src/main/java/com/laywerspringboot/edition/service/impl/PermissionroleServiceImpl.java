package com.laywerspringboot.edition.service.impl;

import com.laywerspringboot.edition.dao.PermissionroleDao;
import com.laywerspringboot.edition.entity.Permissionrole;
import com.laywerspringboot.edition.service.PermissionroleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;



/**
 * (Permissionrole)表服务实现类
 *
 * @author makejava
 * @since 2020-10-24 11:09:24
 */
@Service("permissionroleService")
public class PermissionroleServiceImpl implements PermissionroleService {
    @Resource
    private PermissionroleDao permissionroleDao;

    /**
     * 通过ID查询单条数据
     *
     * @param rId 主键
     * @return 实例对象
     */
    @Override
    public Permissionrole queryById(Integer rId) {
        return this.permissionroleDao.queryById(rId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Permissionrole> queryAllByLimit(int offset, int limit) {
        return this.permissionroleDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param permissionrole 实例对象
     * @return 实例对象
     */
    @Override
    public Permissionrole insert(Permissionrole permissionrole) {
        this.permissionroleDao.insert(permissionrole);
        return permissionrole;
    }

    /**
     * 修改数据
     *
     * @param permissionrole 实例对象
     * @return 实例对象
     */
    @Override
    public Permissionrole update(Permissionrole permissionrole) {
        this.permissionroleDao.update(permissionrole);
        return this.queryById(permissionrole.getRId());
    }

    /**
     * 通过主键删除数据
     *
     * @param rId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer rId) {
        return this.permissionroleDao.deleteById(rId) > 0;
    }
}