package com.laywerspringboot.edition.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * (Prices)实体类
 *
 * @author makejava
 * @since 2020-11-12 20:49:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prices implements Serializable {
    private static final long serialVersionUID = -86964540352065647L;
    /**
     * 费用标准
     */
    private BigDecimal price;
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



}