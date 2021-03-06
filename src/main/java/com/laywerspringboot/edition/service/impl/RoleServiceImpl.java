package com.laywerspringboot.edition.service.impl;

import com.laywerspringboot.edition.dao.RoleDao;
import com.laywerspringboot.edition.entity.Role;
import com.laywerspringboot.edition.entity.dto.RegisterUser;
import com.laywerspringboot.edition.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Role)表服务实现类
 *
 * @author makejava
 * @since 2020-10-24 11:09:25
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleDao roleDao;

    /**
     * 判断用户角色并插入
     * @param user
     *
     */
    public Role insertRole(RegisterUser user) {
        Role role = new Role();
        if (user.getStatus() == -1){
            role.setRolename("报社");
        }
        if (user.getStatus() == 0){
            role.setRolename("用户");
        }
        if (user.getStatus() == 1){
            role.setRolename("法官");
        }
        role.setStatus("1");
        Role role1 = insert(role);
        return role1;
    }


    /**
     * 通过ID查询单条数据
     *
     * @param rId 主键
     * @return 实例对象
     */
    @Override
    public Role queryById(Integer rId) {
        return this.roleDao.queryById(rId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Role> queryAllByLimit(int offset, int limit) {
        return this.roleDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    @Override
    public Role insert(Role role) {
        this.roleDao.insert(role);
        return role;
    }

    /**
     * 修改数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    @Override
    public Role update(Role role) {
        this.roleDao.update(role);
        return this.queryById(role.getRId());
    }

    /**
     * 通过主键删除数据
     *
     * @param rId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer rId) {
        return this.roleDao.deleteById(rId) > 0;
    }
}