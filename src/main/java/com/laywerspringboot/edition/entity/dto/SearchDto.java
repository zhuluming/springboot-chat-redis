package com.laywerspringboot.edition.entity.dto;

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
public class SearchDto implements Serializable {
    private static final long serialVersionUID = 937638654512173L;
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
    /**
     * 法官处理人
     */
    private String lawyer;
    /**
     * 公告名
     */
    private String noticename;
    /**
     * 公告类型(开庭公告)
     */
    private String type;

    /**
     * 发布时间
     */
    /**
     * 是否公告，-1为过期，1为公告,0为未公告
     */
    private String noticeStatus;

}
