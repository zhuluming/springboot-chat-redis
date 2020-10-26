package com.laywerspringboot.edition.dao;

import com.laywerspringboot.edition.entity.Uuidnum;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * (Uuidnum)表数据库访问层
 *
 * @author makejava
 * @since 2020-10-25 17:11:16
 */
public interface UuidnumDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Uuidnum queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Uuidnum> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param uuidnum 实例对象
     * @return 对象列表
     */
    List<Uuidnum> queryAll(Uuidnum uuidnum);

    /**
     * 新增数据
     *
     * @param uuidnum 实例对象
     * @return 影响行数
     */
    int insert(Uuidnum uuidnum);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Uuidnum> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Uuidnum> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Uuidnum> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<Uuidnum> entities);

    /**
     * 修改数据
     *
     * @param uuidnum 实例对象
     * @return 影响行数
     */
    int update(Uuidnum uuidnum);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}