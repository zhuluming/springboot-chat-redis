package com.laywerspringboot.edition.service;

import com.laywerspringboot.edition.entity.Case;

import java.util.List;

/**
 * (Case)表服务接口
 *
 * @author makejava
 * @since 2020-10-24 11:09:21
 */
public interface CaseService {

    /**
     * 通过ID查询单条数据
     *
     * @param caseid 主键
     * @return 实例对象
     */
    Case queryById(String caseid);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Case> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param cases 实例对象
     * @return 实例对象
     */
    Case insert(Case cases);

    /**
     * 修改数据
     *
     * @param cases 实例对象
     * @return 实例对象
     */
    Case update(Case cases);

    /**
     * 通过主键删除数据
     *
     * @param caseid 主键
     * @return 是否成功
     */
    boolean deleteById(String caseid);

}