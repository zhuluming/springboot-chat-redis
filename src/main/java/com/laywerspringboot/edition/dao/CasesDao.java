package com.laywerspringboot.edition.dao;

import com.laywerspringboot.edition.entity.Cases;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * (Cases)表数据库访问层
 *
 * @author makejava
 * @since 2020-10-31 17:20:10
 */
public interface CasesDao {

    /**
     * 通过ID查询单条数据
     *
     * @param cid 主键
     * @return 实例对象
     */
    Cases queryById(Integer cid);
    /**
     * 通过caseID查询单条数据
     *
     * @param CaseId
     * @return 实例对象
     */
    Cases queryByCaseId(String CaseId);


    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Cases> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param cases 实例对象
     * @return 对象列表
     */
    List<Cases> queryAll(Cases cases);

    /**
     * 新增数据
     *
     * @param cases 实例对象
     * @return 影响行数
     */
    int insert(Cases cases);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Cases> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Cases> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Cases> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<Cases> entities);

    /**
     * 修改数据
     *
     * @param cases 实例对象
     * @return 影响行数
     */
    int update(Cases cases);

    /**
     * 通过主键删除数据
     *
     * @param cid 主键
     * @return 影响行数
     */
    int deleteById(Integer cid);
    /**
     * 通过用户真名查询
     * @param tokenRealName
     * @return
     */
    Cases queryByParty(String tokenRealName);


}