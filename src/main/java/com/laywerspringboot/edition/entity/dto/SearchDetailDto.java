package com.laywerspringboot.edition.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "搜索详情",description = "SearchDetailDto")
public class SearchDetailDto implements Serializable {
    private static final long serialVersionUID = 937232326469899873L;
    /**
     * 案号
     */
    @ApiModelProperty(name="caseid",example = "案号")
    private String caseid;
    /**
     * 当事人
     */
    @ApiModelProperty(name="party",example = "张三")
    private String party;
    /**
     * 手机号
     */
    @ApiModelProperty(name="phone",example = "XXXX前端正则校验")
    private String phone;
    /**
     * 当事人身份证号
     */
    @ApiModelProperty(name="idcard",example = "XXXXXX")
    private String idcard;
    /**
     * 所在法院
     */
    @ApiModelProperty(name="court",example = "XXX法院")
    private String court;
    /**
     * 登报区域
     */
    @ApiModelProperty(name="region",example = "XXX")
    private String region;
    /**
     * 法官处理人
     */
    @ApiModelProperty(name="lawyer",example = "李四")
    private String lawyer;

    /**
     * 公告剩余时间
     */
    @ApiModelProperty(name="remainingTime",example = "单位小时")
    private int remainingTime;
    /**
     * 是否公告，-1为过期，0为待公告1为公告
     */
    @ApiModelProperty(name="noticeStatus",example = "-1为过期，0为待公告1为公告")
    private String noticeStatus;
    /**
     * 是否公告，-1为过期，0为待公告1为公告
     */
    @ApiModelProperty(name="address",example = "案件URL")
    private String address;

    /**
     * 支付状态 1是已支付，0是未支付
     */
    @ApiModelProperty(name="state",example = "0")
    private String state;

    public SearchDetailDto(String caseid, String party, String phone, String idcard, String court, String region, String lawyer,  String noticeStatus, String state) {
        this.caseid = caseid;
        this.party = party;
        this.phone = phone;
        this.idcard = idcard;
        this.court = court;
        this.region = region;
        this.lawyer = lawyer;
        this.noticeStatus = noticeStatus;
        this.state = state;
    }
}
