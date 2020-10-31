package com.laywerspringboot.edition.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (Role)实体类
 *
 * @author makejava
 * @since 2020-10-23 18:49:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "用户对象",description = "用户表")
public class Role implements Serializable {
    private static final long serialVersionUID = 516494247796084270L;
    /**
     * 角色id
     */
    @ApiModelProperty(name="id",example = "1")
    private Integer rId;
    /**
     * 角色名称
     */
    @ApiModelProperty(name="rolename",example = "角色")
    private String rolename;
    /**
     * 角色状态
     */
     @ApiModelProperty(name="status",example = "1 代表正常 0 代表异常")
    private String status;



}