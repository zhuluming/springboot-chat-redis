package com.laywerspringboot.edition.service.impl;

import com.laywerspringboot.edition.dao.PricesDao;
import com.laywerspringboot.edition.entity.Prices;
import com.laywerspringboot.edition.service.PricesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;



/**
 * (Prices)表服务实现类
 *
 * @author makejava
 * @since 2020-10-24 11:09:24
 */
@Service("pricesService")
public class PricesServiceImpl implements PricesService {
    @Resource
    private PricesDao pricesDao;

    /**
     * 通过ID查询单条数据
     *
     * @param page 主键
     * @return 实例对象
     */
    @Override
    public Prices queryById(String page) {
        return this.pricesDao.queryById(page);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Prices> queryAllByLimit(int offset, int limit) {
        return this.pricesDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param prices 实例对象
     * @return 实例对象
     */
    @Override
    public Prices insert(Prices prices) {
        this.pricesDao.insert(prices);
        return prices;
    }

    /**
     * 修改数据
     *
     * @param prices 实例对象
     * @return 实例对象
     */
    @Override
    public Prices update(Prices prices) {
        this.pricesDao.update(prices);
        return this.queryById(prices.getPage());
    }

    /**
     * 通过主键删除数据
     *
     * @param page 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String page) {
        return this.pricesDao.deleteById(page) > 0;
    }
}