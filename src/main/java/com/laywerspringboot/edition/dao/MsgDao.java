package com.laywerspringboot.edition.dao;


import com.laywerspringboot.edition.entity.Msg;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * (Msg)表数据库访问层
 *
 * @author makejava
 * @since 2020-11-14 22:07:28
 */
public interface MsgDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Msg queryById(Integer id);

    /**
     * 根据名字查询是否在线
     * @param name
     * @return
     */
    Msg queryByName(String name);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Msg> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param msg 实例对象
     * @return 对象列表
     */
    List<Msg> queryAll(Msg msg);

    /**
     * 新增数据
     *
     * @param msg 实例对象
     * @return 影响行数
     */
    int insert(Msg msg);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Msg> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Msg> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Msg> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<Msg> entities);

    /**
     * 修改数据
     *
     * @param msg 实例对象
     * @return 影响行数
     */
    int update(Msg msg);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}