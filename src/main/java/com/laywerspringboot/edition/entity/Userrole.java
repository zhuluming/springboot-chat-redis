package com.laywerspringboot.edition.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (Userrole)实体类
 *
 * @author makejava
 * @since 2020-10-23 18:49:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Userrole implements Serializable {
    private static final long serialVersionUID = -35410727512602177L;
    /**
     * 主键id
     */
    @ApiModelProperty(name="Id",example = "1")
    private Integer Id;
    /**
     * 用户id
     */
    @ApiModelProperty(name="uId",example = "1")
    private Integer uId;
    /**
     * 角色id
     */
    @ApiModelProperty(name="rId",example = "1")
    private Integer rId;



}