package com.laywerspringboot.edition.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author:小七
 * @createTime:2020-10-31-15-24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "用户搜索",description = "UserSearchDto")
public class UserSearchDto implements Serializable {
    private static final long serialVersionUID = 9376343454512173L;
    /**
     * 案号
     */
    @ApiModelProperty(name="caseid",example = "1")
    private String caseid;
    /**
     * 当事人
     */
    @ApiModelProperty(name="caseid",example = "张三")
    private String party;

    /**
     * 法官处理人
     */
    @ApiModelProperty(name="caseid",example = "李四")
    private String lawyer;

    /**
     * 公告名称
     */
    @ApiModelProperty(name="noticename",example = "ZXXX的XXX")
    private String noticename;

    /**
     * 公告类型
     */
    @ApiModelProperty(name="type",example = "开庭公告")
    private String type;

    /**
     * 是否公告，-1为过期，1为公告,0为未公告
     */
    @ApiModelProperty(name="noticeStatus",example = "-1为过期，1为公告,0为未公告")
    private String noticeStatus;

    /**
     * 登报状态 1已登，0未登
     */
    @ApiModelProperty(name="finished",example = "登报状态 1已登，0未登")
    private String finished;
    /**
     * 付费状态 0 为未付费 1为付费
     */
    @ApiModelProperty(name="state",example = "付费状态状态0 为未付费 1为付费")
    private String state;
}
