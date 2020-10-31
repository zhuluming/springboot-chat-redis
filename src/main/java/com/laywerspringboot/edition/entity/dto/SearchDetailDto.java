package com.laywerspringboot.edition.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author:小七
 * @createTime:2020-10-31-16-09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchDetailDto implements Serializable {
    private static final long serialVersionUID = 937232326469899873L;
    /**
     * 案号
     */
    private String caseid;
    /**
     * 当事人
     */
    private String party;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 所在法院
     */
    private String court;
    /**
     * 登报区域
     */
    private String region;
    /**
     * 法官处理人
     */
    private String lawyer;
    /**
     * 客服
     */
    private String admin;

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
    private Object releasetime;
    /**
     * 是否公告，-1为过期，1为公告
     */
    private String noticeStatus;
    /**
     * 报纸id
     */
    private Integer pId;

    /**
     * 报社
     */
    private String place;
    /**
     * 版面
     */
    private String page;
    /**
     * 支付状态
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
