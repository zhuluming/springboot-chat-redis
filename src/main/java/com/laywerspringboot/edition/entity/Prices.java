package com.laywerspringboot.edition.entity;

import java.io.Serializable;

/**
 * (Prices)实体类
 *
 * @author makejava
 * @since 2020-11-12 20:49:28
 */
public class Prices implements Serializable {
    private static final long serialVersionUID = -86964540352065647L;
    /**
     * 费用标准
     */
    private Double price;
    /**
     * 版面，对应报纸表中版面
     */
    private String page;
    /**
     * 支付状态
     */
    private String state;
    /**
     * 主键
     */
    private Integer priceid;
    /**
     * 案号，同时也充当订单号作用
     */
    private String caseid;


    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getPriceid() {
        return priceid;
    }

    public void setPriceid(Integer priceid) {
        this.priceid = priceid;
    }

    public String getCaseid() {
        return caseid;
    }

    public void setCaseid(String caseid) {
        this.caseid = caseid;
    }

}