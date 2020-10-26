package com.laywerspringboot.edition.service.impl;

import com.laywerspringboot.edition.dao.CaseDao;
import com.laywerspringboot.edition.entity.Case;
import com.laywerspringboot.edition.service.CaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;



/**
 * (Case)表服务实现类
 *
 * @author makejava
 * @since 2020-10-24 11:09:21
 */
@Service("caseService")
public class CaseServiceImpl implements CaseService {
    @Resource
    private CaseDao caseDao;

    /**
     * 通过ID查询单条数据
     *
     * @param caseid 主键
     * @return 实例对象
     */
    @Override
    public Case queryById(String caseid) {
        return this.caseDao.queryById(caseid);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Case> queryAllByLimit(int offset, int limit) {
        return this.caseDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param cases 实例对象
     * @return 实例对象
     */
    @Override
    public Case insert(Case cases) {
        this.caseDao.insert(cases);
        return cases;
    }

    /**
     * 修改数据
     *
     * @param cases 实例对象
     * @return 实例对象
     */
    @Override
    public Case update(Case cases) {
        this.caseDao.update(cases);
        return this.queryById(cases.getCaseid());
    }

    /**
     * 通过主键删除数据
     *
     * @param caseid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String caseid) {
        return this.caseDao.deleteById(caseid) > 0;
    }
}