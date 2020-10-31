package com.laywerspringboot.edition.service;

import com.laywerspringboot.edition.entity.Cases;

import java.util.List;



/**
 * (Cases)表服务接口
 *
 * @author makejava
 * @since 2020-10-31 17:19:09
 */
public interface CasesService {

    /**
     * 通过ID查询单条数据
     *
     * @param cid 主键
     * @return 实例对象
     */
    Cases queryById(Integer cid);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Cases> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param cases 实例对象
     * @return 实例对象
     */
    Cases insert(Cases cases);

    /**
     * 修改数据
     *
     * @param cases 实例对象
     * @return 实例对象
     */
    Cases update(Cases cases);

    /**
     * 通过主键删除数据
     *
     * @param cid 主键
     * @return 是否成功
     */
    boolean deleteById(Integer cid);

}