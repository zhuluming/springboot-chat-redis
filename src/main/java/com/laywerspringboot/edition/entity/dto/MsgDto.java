package com.laywerspringboot.edition.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author:小七
 * @createTime:2020-11-08-16-05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "消息",description = "MsgDto")
public class MsgDto implements Serializable {
    //msg 用户每次发送的消息体，里面包用户信息，用户名字，用户身份，用户是否开启消息推送，发送对象的名字
    @ApiModelProperty(name="msg",example = "hello world")
    private String msg;
    private String userName;
    private String role;
    @ApiModelProperty(name="status",example = "1开启，0关闭")
    private String status;
    @ApiModelProperty(name="toName",example = "聊天对象的名字")
    private String toName;

}
