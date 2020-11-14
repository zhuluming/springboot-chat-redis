package com.laywerspringboot.edition.entity.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @Author:小七
 * @createTime:2020-11-08-20-14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "信息标志位",description = "MsgFlag")
public class MsgFlag implements Serializable {
    private ArrayList<Long> times = new ArrayList<>();
    //标志位，定义随时中断的位置 0为未读，1为以读
    private ArrayList<Integer> flag = new ArrayList<>();
    //定义存redis的key
    private String key;
    //定义用户的状态是否在聊天框
    private Integer status;
}
