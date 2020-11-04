package com.laywerspringboot.edition.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (Newspaper)实体类
 *
 * @author makejava
 * @since 2020-11-01 13:31:49
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Newspaper implements Serializable {
    private static final long serialVersionUID = 615949231800172033L;
    /**
     * 报纸id
     */
    private Integer pId;
    /**
     * 登报区域
     */
    private String region;
    /**
     * 报社
     */
    private String place;
    /**
     * 版面
     */
    private String page;
    /**
     * 付费状态  0 为未付费 1为付费
     */
    private String state;
    /**
     * 发票明细
     */
    private String detail;
    /**
     * 寄送地址
     */
    private String address;
    /**
     * 公告天数
     */
    private Integer day;



}