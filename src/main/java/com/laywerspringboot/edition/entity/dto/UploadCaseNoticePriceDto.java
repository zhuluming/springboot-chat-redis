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
@ApiModel(value = "上传案件",description = "UploadCaseNoticePriceDto")
public class UploadCaseNoticePriceDto implements Serializable {
    private static final long serialVersionUID = 93723243244899873L;
    /**
     * 公告名
     */
    @ApiModelProperty(name="noticename",example = "XXXXXX")
    private String noticename;
    /**
     * 公告类型(开庭公告)
     */
    @ApiModelProperty(name="type",example = "开庭公告")
    private String type;
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
     * 手机号
     */
    @ApiModelProperty(name="phone",example = "186565656")
    private String phone;
    /**
     * 身份证号，前端校验是否合法
     */
    @ApiModelProperty(name="idcard",example = "18656564545456")
    private String idcard;
    /**
     * 当事人
     */
    @ApiModelProperty(name="caseid",example = "张三")
    private String party;

    /**
     * 案号
     */
    @ApiModelProperty(name="caseid",example = "1")
    private String caseid;

    /**
     * 公告图片
     */
    @ApiModelProperty(name="picture",example = "前端给的url")
    private String picture;
    /**
     * 法官处理人
     */
    @ApiModelProperty(name="lawyer",example = "李四")
    private String lawyer;
    /**
     * 公告类别  刑事公告/民事公告
     */
    @ApiModelProperty(name="category",example = "刑事公告/民事公告")
    private String category;

    /**
     * 发布天数
     */
    @ApiModelProperty(name="day",example = "1")
    private int day;


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
