package com.laywerspringboot.edition.dao;

import com.laywerspringboot.edition.entity.Newspaper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * (Newspaper)表数据库访问层
 *
 * @author makejava
 * @since 2020-10-24 11:05:25
 */
public interface NewspaperDao {

    /**
     * 通过ID查询单条数据
     *
     * @param pId 主键
     * @return 实例对象
     */
    Newspaper queryById(Integer pId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Newspaper> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param newspaper 实例对象
     * @return 对象列表
     */
    List<Newspaper> queryAll(Newspaper newspaper);

    /**
     * 新增数据
     *
     * @param newspaper 实例对象
     * @return 影响行数
     */
    int insert(Newspaper newspaper);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Newspaper> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Newspaper> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Newspaper> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<Newspaper> entities);

    /**
     * 修改数据
     *
     * @param newspaper 实例对象
     * @return 影响行数
     */
    int update(Newspaper newspaper);

    /**
     * 通过主键删除数据
     *
     * @param pId 主键
     * @return 影响行数
     */
    int deleteById(Integer pId);

}