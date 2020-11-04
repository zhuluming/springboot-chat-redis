package com.laywerspringboot.edition.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (Cases)实体类
 *
 * @author makejava
 * @since 2020-10-23 18:49:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cases implements Serializable {
    private static final long serialVersionUID = 937638663269899873L;
    /**
     * 主键
     */
    private int cid;
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
     * 身份证号，前端校验是否合法
     */
    @ApiModelProperty(name="idcard",example = "18656564545456")
    private String idcard;
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



}