package com.laywerspringboot.edition.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (Jurisdiction)实体类
 *
 * @author makejava
 * @since 2020-10-23 18:49:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Jurisdiction implements Serializable {
    private static final long serialVersionUID = 308191882278455293L;
    /**
     * 权限id
     */
    private Integer jId;
    /**
     * 上传公告
     */
    private String sysUpload;
    /**
     * 展示公告
     */
    private String sysExhibition;
    /**
     * 修改
     */
    private String sysModify;
    /**
     * 删除公告
     */
    private String sysDelete;
    /**
     * 是否锁底，1锁0不锁
     */
    private Integer jStatus;



}