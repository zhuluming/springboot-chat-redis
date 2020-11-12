package com.laywerspringboot.edition.dao;

import com.laywerspringboot.edition.entity.Prices;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * (Prices)表数据库访问层
 *
 * @author makejava
 * @since 2020-11-12 21:43:57
 */
public interface PricesDao {

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
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Prices> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param prices 实例对象
     * @return 对象列表
     */
    List<Prices> queryAll(Prices prices);

    /**
     * 新增数据
     *
     * @param prices 实例对象
     * @return 影响行数
     */
    int insert(Prices prices);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Prices> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Prices> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Prices> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<Prices> entities);

    /**
     * 修改数据
     *
     * @param prices 实例对象
     * @return 影响行数
     */
    int update(Prices prices);

    /**
     * 通过主键删除数据
     *
     * @param priceid 主键
     * @return 影响行数
     */
    int deleteById(Integer priceid);

}