package com.laywerspringboot.edition.service;

import com.laywerspringboot.edition.entity.Jurisdiction;

import java.util.List;


/**
 * (Jurisdiction)表服务接口
 *
 * @author makejava
 * @since 2020-10-24 11:09:23
 */
public interface JurisdictionService {

    /**
     * 通过ID查询单条数据
     *
     * @param jId 主键
     * @return 实例对象
     */
    Jurisdiction queryById(Integer jId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Jurisdiction> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param jurisdiction 实例对象
     * @return 实例对象
     */
    Jurisdiction insert(Jurisdiction jurisdiction);

    /**
     * 修改数据
     *
     * @param jurisdiction 实例对象
     * @return 实例对象
     */
    Jurisdiction update(Jurisdiction jurisdiction);

    /**
     * 通过主键删除数据
     *
     * @param jId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer jId);

}