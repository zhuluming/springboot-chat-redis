package com.laywerspringboot.edition.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (Permissionrole)实体类
 *
 * @author makejava
 * @since 2020-10-23 18:49:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permissionrole implements Serializable {
    private static final long serialVersionUID = -84652270090878468L;
    /**
     * 角色id
     */
    private Integer rId;
    /**
     * 权限id
     */
    private Integer jId;



}