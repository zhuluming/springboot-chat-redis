package com.laywerspringboot.edition.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (Uuidnum)实体类
 *
 * @author makejava
 * @since 2020-10-25 17:11:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Uuidnum implements Serializable {
    private static final long serialVersionUID = -55733382122154200L;

    private Integer id;

    private String phoneid;

    private Integer num;

    private Long time;

    private Long endtime;




}