package com.laywerspringboot.edition.service;

import com.laywerspringboot.edition.entity.Userrole;

import java.util.List;



/**
 * (Userrole)表服务接口
 *
 * @author makejava
 * @since 2020-10-24 11:09:25
 */
public interface UserroleService {

    /**
     * 通过ID查询单条数据
     *
     * @param uId 主键
     * @return 实例对象
     */
    Userrole queryById(Integer uId);



    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Userrole> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param userrole 实例对象
     * @return 实例对象
     */
    Userrole insert(Userrole userrole);

    /**
     * 修改数据
     *
     * @param userrole 实例对象
     * @return 实例对象
     */
    Userrole update(Userrole userrole);

    /**
     * 通过主键删除数据
     *
     * @param uId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer uId);

}