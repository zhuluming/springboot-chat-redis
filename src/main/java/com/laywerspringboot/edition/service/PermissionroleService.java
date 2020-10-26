package com.laywerspringboot.edition.service;

import com.laywerspringboot.edition.entity.Permissionrole;

import java.util.List;



/**
 * (Permissionrole)表服务接口
 *
 * @author makejava
 * @since 2020-10-24 11:09:24
 */
public interface PermissionroleService {

    /**
     * 通过ID查询单条数据
     *
     * @param rId 主键
     * @return 实例对象
     */
    Permissionrole queryById(Integer rId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Permissionrole> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param permissionrole 实例对象
     * @return 实例对象
     */
    Permissionrole insert(Permissionrole permissionrole);

    /**
     * 修改数据
     *
     * @param permissionrole 实例对象
     * @return 实例对象
     */
    Permissionrole update(Permissionrole permissionrole);

    /**
     * 通过主键删除数据
     *
     * @param rId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer rId);

}