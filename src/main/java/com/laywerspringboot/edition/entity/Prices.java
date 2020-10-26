package com.laywerspringboot.edition.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (Prices)实体类
 *
 * @author makejava
 * @since 2020-10-23 18:49:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prices implements Serializable {
    private static final long serialVersionUID = -84009318308852524L;
    /**
     * 费用标准
     */
    private Double price;
    /**
     * 版面，对应报纸表中版面
     */
    private String page;


}