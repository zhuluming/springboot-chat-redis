package com.laywerspringboot.edition.service;

import com.laywerspringboot.edition.entity.Role;
import com.laywerspringboot.edition.entity.dto.RegisterUser;

import java.util.List;

/**
 * (Role)表服务接口
 *
 * @author makejava
 * @since 2020-10-24 11:09:24
 */
public interface RoleService {
    /**
     * 判断用户角色并插入
     * @param user
     */
     Role insertRole(RegisterUser user);
    /**
     * 通过ID查询单条数据
     *
     * @param rId 主键
     * @return 实例对象
     */
    Role queryById(Integer rId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Role> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    Role insert(Role role);

    /**
     * 修改数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    Role update(Role role);

    /**
     * 通过主键删除数据
     *
     * @param rId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer rId);

}