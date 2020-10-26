package com.laywerspringboot.edition.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (Newspaper)实体类
 *
 * @author makejava
 * @since 2020-10-23 18:49:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Newspaper implements Serializable {
    private static final long serialVersionUID = -36732893728961730L;
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
     * 状态
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




}