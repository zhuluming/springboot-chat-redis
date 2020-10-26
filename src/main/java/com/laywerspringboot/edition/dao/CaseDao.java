package com.laywerspringboot.edition.dao;

import com.laywerspringboot.edition.entity.Case;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * (Case)表数据库访问层
 *
 * @author makejava
 * @since 2020-10-24 11:12:44
 */
public interface CaseDao {

    /**
     * 通过ID查询单条数据
     *
     * @param caseid 主键
     * @return 实例对象
     */
    Case queryById(String caseid);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Case> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param cases 实例对象
     * @return 对象列表
     */
    List<Case> queryAll(Case cases);

    /**
     * 新增数据
     *
     * @param cases 实例对象
     * @return 影响行数
     */
    int insert(Case cases);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Case> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Case> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Case> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<Case> entities);

    /**
     * 修改数据
     *
     * @param cases 实例对象
     * @return 影响行数
     */
    int update(Case cases);

    /**
     * 通过主键删除数据
     *
     * @param caseid 主键
     * @return 影响行数
     */
    int deleteById(String caseid);

}