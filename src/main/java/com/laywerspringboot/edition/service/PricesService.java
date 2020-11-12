package com.laywerspringboot.edition.service;

import com.laywerspringboot.edition.entity.Prices;

import java.util.List;


/**
 * (Prices)表服务接口
 *
 * @author makejava
 * @since 2020-11-12 21:45:16
 */
public interface PricesService {

    /**
     * 通过ID查询单条数据
     *
     * @param priceid 主键
     * @return 实例对象
     */
    Prices queryById(Integer priceid);

    /**
     * 查询指定案号数据
     * @param caseId 案号
     * @return
     */
    Prices queryByCaseId(String caseId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Prices> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param prices 实例对象
     * @return 实例对象
     */
    Prices insert(Prices prices);

    /**
     * 修改数据
     *
     * @param prices 实例对象
     * @return 实例对象
     */
    Prices update(Prices prices);

    /**
     * 通过主键删除数据
     *
     * @param priceid 主键
     * @return 是否成功
     */
    boolean deleteById(Integer priceid);

}