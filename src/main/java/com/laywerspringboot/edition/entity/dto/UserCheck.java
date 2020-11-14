package com.laywerspringboot.edition.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author:小七
 * @createTime:2020-10-31-16-09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "发布前的",description = "UserCheck")
public class UserCheck implements Serializable {

    private static final long serialVersionUID = 937243343244899873L;
    /**
     *登报报社
     */

    @ApiModelProperty(name="place",example = "登报报社")
    private String place;

    /**
     *寄送地址
     */

    @ApiModelProperty(name="address",example = "寄送地址")
    private String address;

    /**
     *发票明细
     */

    @ApiModelProperty(name="detail",example = "发票明细")
    private String detail;

    /**
     *案号
     */

    @ApiModelProperty(name="caseId",example = "案号")
    private String caseId;
    /**
     *发票明细
     */

    @ApiModelProperty(name="page",example = "版面")
    private String page;

    /**
     *案号
     */

    @ApiModelProperty(name="price",example = "金额")
    private Double price;





    /**
     * 支付状态,1是已支付，0是未支付,当法官发布时应该是未支付
     */
    private String state ;


    /**
     * 发布时间
     */
    private Date releasetime;
    /**
     * 是否公告，-1为过期，1为公告,0为未公告
     */
    private String noticeStatus;

   /**
     * newspaperid,用来和notice的id做对应
     */
    private Integer nid;
    /**
     * 登报状态
     * 登报状态 1已登，0未登
     */
    private String finished;

}
