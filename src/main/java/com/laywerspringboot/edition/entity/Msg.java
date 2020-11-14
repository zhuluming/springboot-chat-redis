package com.laywerspringboot.edition.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (Msg)实体类
 *
 * @author makejava
 * @since 2020-11-14 22:07:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Msg implements Serializable {
    private static final long serialVersionUID = 338186533088954560L;

    private Integer id;

    private String name;

    private String state;


}