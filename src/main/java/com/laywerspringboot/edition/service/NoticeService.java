package com.laywerspringboot.edition.service;

import com.laywerspringboot.edition.entity.Notice;

import java.util.List;



/**
 * (Notice)表服务接口
 *
 * @author makejava
 * @since 2020-10-24 11:09:24
 */
public interface NoticeService {

    /**
     * 通过ID查询单条数据
     *
     * @param nId 主键
     * @return 实例对象
     */
    Notice queryById(Integer nId);

    /**
     * 通过caseaddress查询单条数据
     *
     * @param caseaddress
     * @return 实例对象
     */
    Notice queryByCaseAddress(String caseaddress);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Notice> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param notice 实例对象
     * @return 实例对象
     */
    Notice insert(Notice notice);

    /**
     * 修改数据
     *
     * @param notice 实例对象
     * @return 实例对象
     */
    Notice update(Notice notice);

    /**
     * 通过主键删除数据
     *
     * @param nId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer nId);

}