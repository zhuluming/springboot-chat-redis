package com.laywerspringboot.edition.dao;

import com.laywerspringboot.edition.entity.Userrole;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * (Userrole)表数据库访问层
 *
 * @author makejava
 * @since 2020-10-27 17:28:18
 */
public interface UserroleDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Userrole queryById(Integer id);

    /**
     * 通过UID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Userrole queryByRId(Integer id);

    /**
     * 通过RID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Userrole queryByUId(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Userrole> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param userrole 实例对象
     * @return 对象列表
     */
    List<Userrole> queryAll(Userrole userrole);

    /**
     * 新增数据
     *
     * @param userrole 实例对象
     * @return 影响行数
     */
    int insert(Userrole userrole);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Userrole> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Userrole> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Userrole> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<Userrole> entities);

    /**
     * 修改数据
     *
     * @param userrole 实例对象
     * @return 影响行数
     */
    int update(Userrole userrole);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}