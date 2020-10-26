package com.laywerspringboot.edition.service;

import com.laywerspringboot.edition.entity.Newspaper;

import java.util.List;



/**
 * (Newspaper)表服务接口
 *
 * @author makejava
 * @since 2020-10-24 11:09:23
 */
public interface NewspaperService {

    /**
     * 通过ID查询单条数据
     *
     * @param pId 主键
     * @return 实例对象
     */
    Newspaper queryById(Integer pId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Newspaper> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param newspaper 实例对象
     * @return 实例对象
     */
    Newspaper insert(Newspaper newspaper);

    /**
     * 修改数据
     *
     * @param newspaper 实例对象
     * @return 实例对象
     */
    Newspaper update(Newspaper newspaper);

    /**
     * 通过主键删除数据
     *
     * @param pId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer pId);

}