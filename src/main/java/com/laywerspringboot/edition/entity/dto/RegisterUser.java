package com.laywerspringboot.edition.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author:小七
 * @createTime:2020-10-23-21-43
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "注册用户对象",description = "dto")
public class RegisterUser implements Serializable {
    private static final long serialVersionUID = -19045543L;

    /**
     * 用户名称
     */
    @ApiModelProperty(name="id",example = "三七/siqi")
    private String username;
    /**
     * 用户密码
     */
    @ApiModelProperty(name="password",example = "qwer")
    private String password;
    /**
     * 用户真实姓名
     */
    @ApiModelProperty(name="realname",example = "思琦")
    private String realname;
    /**
     * 确认密码
     */
    @ApiModelProperty(name="replynewpassword",example = "qwer")
    private String replynewpassword;
    /**
     * 手机号
     */
    @ApiModelProperty(name="phoneid",example = "18279444776")
    private String phoneid;
    /**
     * 身份证
     */
    @ApiModelProperty(name="idcard",example = "544645.....")
    private String idcard;
    /**
     * 用户头像地址
     */
    @ApiModelProperty(name="photoaddress",example = "......")
    private String photoaddress;
    /**
     * 用户身份
     * 当-1代表报社，0代表用户，1代表法官
     */
    @ApiModelProperty(name="status",example = "-1代表报社，0代表用户，1代表法官")
    private int status;
    /**
     * 修改时间
     */
    //private Date altertime;
    /**
     * 用户验证码，每次验证完删除
     */
    //private String uuid;



}
