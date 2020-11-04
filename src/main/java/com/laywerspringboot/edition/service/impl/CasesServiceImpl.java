package com.laywerspringboot.edition.service.impl;

import com.laywerspringboot.edition.dao.CasesDao;
import com.laywerspringboot.edition.entity.Cases;
import com.laywerspringboot.edition.service.CasesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Cases)表服务实现类
 *
 * @author makejava
 * @since 2020-10-31 17:19:10
 */
@Service("casesService")
public class CasesServiceImpl implements CasesService {
    @Resource
    private CasesDao casesDao;

    /**
     * 通过ID查询单条数据
     *
     * @param cid 主键
     * @return 实例对象
     */
    @Override
    public Cases queryById(Integer cid) {
        return this.casesDao.queryById(cid);
    }

    @Override
    public Cases queryByCaseId(String CaseId) {
        return this.casesDao.queryByCaseId(CaseId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Cases> queryAllByLimit(int offset, int limit) {
        return this.casesDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param cases 实例对象
     * @return 实例对象
     */
    @Override
    public Cases insert(Cases cases) {
        this.casesDao.insert(cases);
        return cases;
    }

    /**
     * 修改数据
     *
     * @param cases 实例对象
     * @return 实例对象
     */
    @Override
    public Cases update(Cases cases) {
        this.casesDao.update(cases);
        return this.queryById(cases.getCid());
    }

    /**
     * 通过主键删除数据
     *
     * @param cid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer cid) {
        return this.casesDao.deleteById(cid) > 0;
    }
    /**
     * 通过用户真名查询
     * @param tokenRealName
     * @return
     */
    @Override
    public Cases queryByParty(String tokenRealName) {
        return this.casesDao.queryByParty(tokenRealName);
    }
}