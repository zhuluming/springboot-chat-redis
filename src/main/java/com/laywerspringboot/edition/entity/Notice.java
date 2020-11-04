package com.laywerspringboot.edition.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * (Notice)实体类
 *
 * @author makejava
 * @since 2020-10-23 18:49:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notice implements Serializable {
    private static final long serialVersionUID = 763410781576385620L;
    /**
     * 公告id
     */
    private Integer nId;
    /**
     * 公告名
     */
    private String noticename;
    /**
     * 公告类型(开庭公告)
     */
    private String type;
    /**
     * 案号
     */
    private String caseaddress;
    /**
     * 公告图片
     */
    private String picture;
    /**
     * 公告类别
     */
    private String category;
    /**
     * 发布时间
     */
    private Date releasetime;
    /**
     * 是否公告，-1为过期，1为公告
     */
    private String noticeStatus;
    /**
     * 登报状态
     * 登报状态 1已登，0未登
     */
    private String finished;




}